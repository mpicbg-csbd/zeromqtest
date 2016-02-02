package zeromqtest.sillygame;

public class ContinuationRequest
{

	private final int askIndex;

	private final double elementAtIndex;

	public ContinuationRequest( final int index, final double value ) {
		this.askIndex = index;
		this.elementAtIndex = value;
	}

	/**
	 * @return the askIndex
	 */
	public int getAskIndex() {
		return askIndex;
	}

	/**
	 * @return the elementAtIndex
	 */
	public double getElementAtIndex() {
		return elementAtIndex;
	}
}
