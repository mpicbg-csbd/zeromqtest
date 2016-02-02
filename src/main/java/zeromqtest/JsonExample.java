package zeromqtest;

import java.io.IOException;

public class JsonExample
{
	public static void main( final String[] args ) throws IOException
	{
		final MyAnswer myAnswer = new MyAnswer( 10, false );

		final byte[] send = JsonBytes.toJson( myAnswer );
		final MyAnswer receive = JsonBytes.fromJson( send, MyAnswer.class );

		System.out.println( myAnswer );
		System.out.println();
		System.out.println( receive );
	}
}
