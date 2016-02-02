/**
 *
 */
package zeromqtest.sillygame;

import zeromqtest.AbstractMessageTypes;

/**
 * @author jug
 */
public class SillyGameMessageTypes extends AbstractMessageTypes {

	public static final int INITIAL_REQUEST = 0;
	public static final int CONTINUATION_REQUEST = 1;
	public static final int QUESTION_RESPONSE = 2;
	public static final int FINAL_RESPONSE = 3;

	public SillyGameMessageTypes() {
		super();
		put( INITIAL_REQUEST, InitialRequest.class );
		put( CONTINUATION_REQUEST, ContinuationRequest.class );
		put( QUESTION_RESPONSE, QuestionResponse.class );
		put( FINAL_RESPONSE, FinalResponse.class );
	}
}
