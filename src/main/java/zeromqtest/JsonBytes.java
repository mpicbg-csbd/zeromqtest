package zeromqtest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonBytes
{
	public static byte[] toJson( final Object obj )
	{
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final OutputStreamWriter writer = new OutputStreamWriter( bos );
		gson.toJson( obj, writer );
		try
		{
			writer.close();
		}
		catch ( final IOException e )
		{}
		return bos.toByteArray();
	}

	public static < T > T fromJson( final byte[] array, final Class< T > klass )
	{
		final Gson gson = new GsonBuilder().create();
		return gson.fromJson( new InputStreamReader( new ByteArrayInputStream( array ) ), klass );
	}
}
