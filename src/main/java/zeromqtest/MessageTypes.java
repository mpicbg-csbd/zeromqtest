/**
 * 
 */
package zeromqtest;

public interface MessageTypes {

	public Class< ? > classForId( int id );

	public int idForClass( Class< ? > klass );
}