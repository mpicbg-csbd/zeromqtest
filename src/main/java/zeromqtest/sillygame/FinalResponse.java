package zeromqtest.sillygame;

public class FinalResponse
{
	double[] correctArray;

	public FinalResponse( final double[] array ) {
		correctArray = array;
	}

	/**
	 * @return
	 */
	public double[] getFinalArray() {
		return correctArray;
	}
}
