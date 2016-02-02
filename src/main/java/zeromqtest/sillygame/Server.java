/**
 *
 */
package zeromqtest.sillygame;

import static zeromqtest.sillygame.SillyGameMessageTypes.CONTINUATION_REQUEST;
import static zeromqtest.sillygame.SillyGameMessageTypes.INITIAL_REQUEST;

import org.zeromq.ZMQ;

import zeromqtest.TypedJsonBytes;
import zeromqtest.TypedJsonBytes.TypedObject;

/**
 * @author jug
 */
public class Server {

	public static void main( final String[] args ) {
		final ZMQ.Context context = ZMQ.context( 1 );

		//  Socket to talk to clients
		final ZMQ.Socket responder = context.socket( ZMQ.REP );
		responder.bind( "tcp://*:5555" );

		final TypedJsonBytes json = new TypedJsonBytes( new SillyGameMessageTypes() );

		double[] currentArray = new double[ 0 ];
		int errorsLeft = 0;
		int currentIndex = 0;

		while ( !Thread.currentThread().isInterrupted() ) {
			final byte[] request = responder.recv( 0 );
			final TypedObject to = json.fromJson( request );

			switch ( to.type() ) {
			case INITIAL_REQUEST:
				final InitialRequest ir = ( InitialRequest ) to.object();
				currentArray = ir.getFaultyArray();
				errorsLeft = ir.getNumErrors();
				currentIndex = 0;
				break;
			case CONTINUATION_REQUEST:
				final ContinuationRequest cr = ( ContinuationRequest ) to.object();
				final int i = cr.getAskIndex();
				final double v = cr.getElementAtIndex();
				if ( currentArray[ i ] != v ) {
					currentArray[ i ] = v;
					errorsLeft--;
				}
				break;
			default:
				throw new IllegalArgumentException( "Muhaha!" );
			}

			responder.send( json.toJson(
					( errorsLeft > 0 ) ? new QuestionResponse( currentIndex++ ) : new FinalResponse( currentArray ) ), 0 );
		}
		responder.close();
		context.term();

	}
}
