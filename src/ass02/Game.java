package ass02;

public class Game implements SlidingBlock {
	
	private Board board;
	private final int boardDimensions;

	public Game(int n){
		if(n < 4) throw new RuntimeException("Minimum of 4 spaces on a board");
		long check = (long)(Math.sqrt(n) + 0.5);
		if(!(check*check == n)) throw new RuntimeException("Not correct dimensions for a perfectly square board");
		boardDimensions = n;
		board = new Board(n);
	}

	@Override
	public int[] solve(int[] start, int[] goal, int maxMoves) {
		if(start.length != boardDimensions && goal.length != boardDimensions) throw new RuntimeException("Board size is inconsistentg with start and goal array sizes");
		board.setBoardSpaceArray(board.layoutSpaces(start));
		board.setupBoardGraph(board.getBoardSpaceArray());
		board.getGraph().fillNodeDistances(new Space(0,0,0));
		board.getGraph().printDistanceFills();
		board.swapTiles(0, 5);
		board.swapTiles(0, 10);
		board.swapTiles(0, 11);
		board.swapTiles(0, 16);		
		return start;
	}
	@Override
	public int[] shortestPath(int startPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addWall(int positionI, int positionJ) {
		board.addWallToBoard(board.findCorrespondingBoardSpace(positionI), board.findCorrespondingBoardSpace(positionJ));
		
	}
	
	@Override
	public String toString(){
		return this.board.toString();
	}
}
