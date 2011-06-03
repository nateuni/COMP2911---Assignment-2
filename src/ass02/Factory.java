package ass02;

public class Factory
{
	/** Make a sliding block puzzle of size n x n. */
	public static SlidingBlock make (int n)
	{
		return new Game(n);		
	}
	
	public static BoardGraph makeBoardGraph(Space[] boardSpaces){
		if(boardSpaces == null) throw new NullPointerException();
		return new BoardGraph(boardSpaces);
	}
	
}
