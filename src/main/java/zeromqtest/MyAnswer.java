package zeromqtest;

public class MyAnswer
{
	private final int guessIndex;

	private final boolean solved;

	public MyAnswer( final int guessIndex, final boolean solved )
	{
		this.guessIndex = guessIndex;
		this.solved = solved;
	}

	@Override
	public String toString()
	{
		return "guessIndex = " + guessIndex + "\n" + "solved = " + solved;
	}
}
