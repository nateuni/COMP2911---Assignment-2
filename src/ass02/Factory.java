package ass02;

public class Factory
{
	protected static Factory theFactory;

	/** Make a sliding block puzzle of size n x n. */
	public static SlidingBlock make (int n)
	{
		theFactory = new Factory();
		return new Board(n);
	}	
	
}
