/**
 *
 */
package zeromqtest.sillygame;

import static zeromqtest.sillygame.SillyGameMessageTypes.FINAL_RESPONSE;
import static zeromqtest.sillygame.SillyGameMessageTypes.QUESTION_RESPONSE;

import java.util.Arrays;

import org.zeromq.ZMQ;

import zeromqtest.TypedJsonBytes;
import zeromqtest.TypedJsonBytes.TypedObject;

/**
 * @author jug
 */
public class Client {

	public static void main( final String[] args ) {
		final ZMQ.Context context = ZMQ.context( 1 );

		//  Socket to talk to server
		System.out.println( "Connecting to hello world server…" );

		final ZMQ.Socket requester = context.socket( ZMQ.REQ );
		requester.connect( "tcp://localhost:5555" );

		final double[] correctArray = new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		final double[] faultyArray = correctArray.clone();
		faultyArray[ 3 ] = 2.8;
		faultyArray[ 7 ] = 7.1;
		faultyArray[ 8 ] = 7.2;

		final TypedJsonBytes json = new TypedJsonBytes( new SillyGameMessageTypes() );

		final InitialRequest ir = new InitialRequest( 3, faultyArray );
		requester.send( json.toJson( ir ), 0 );

		double[] finalArray;
		a: while ( true ) {
			final byte[] reply = requester.recv( 0 );
			final TypedObject to = json.fromJson( reply );

			switch ( to.type() ) {
			case QUESTION_RESPONSE:
				final QuestionResponse qr = ( QuestionResponse ) to.object();
				final int index = qr.getAskIndex();
				final ContinuationRequest cr = new ContinuationRequest( index, correctArray[ index ] );
				requester.send( json.toJson( cr ), 0 );
				break;
			case FINAL_RESPONSE:
				final FinalResponse finalResponse = ( FinalResponse ) to.object();
				finalArray = finalResponse.getFinalArray();
				break a;
			default:
				throw new IllegalArgumentException( "Muhaha!" );
			}
		}

		System.out.println( String.format( "correct array: %s", Arrays.toString( correctArray ) ) );
		System.out.println( String.format( "Final array:   %s", Arrays.toString( finalArray ) ) );

		requester.close();
		context.term();
	}

}
