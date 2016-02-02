package zeromqtest.sillygame;

public class InitialRequest
{

	private final double[] faultyArray;

	private final int numErrors;

	public InitialRequest( final int numErrors, final double[] faultyArray ) {
		this.numErrors = numErrors;
		this.faultyArray = faultyArray;
	}

	/**
	 * @return the faultyArray
	 */
	public double[] getFaultyArray() {
		return faultyArray;
	}

	/**
	 * @return the numErrors
	 */
	public int getNumErrors() {
		return numErrors;
	}
}
