package ass02;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A board has Two Players and up to 20 placed walls (10 per player) and tracks each of their coordinates.
 * @author Team Stump
 */
public class Board extends BoardComponent implements SlidingBlock {

	final boolean VERTICAL = true;
	final boolean HORIZONTAL = false;
	protected LinkedList<Wall> wallList = new LinkedList<Wall>();
	//protected LinkedList<Move> moveList = new LinkedList<Move>();
	protected int moveListIndex = -1;
	public BoardGraph graph;
	
	//private final int size;
	//private final int gridMeasurement;
	//private Space start;
	//private Space goal;
	//private int maxMoves;
	private Space[] boardSpaceArray;
	
	
	public Board(int numberOfSquares){
		if(numberOfSquares < 4) throw new RuntimeException("Minimum of 4 spaces on a board");
		// How to test for perfect square?
		long check = (long)(Math.sqrt(numberOfSquares) + 0.5);
		if(!(check*check == numberOfSquares)) throw new RuntimeException("Not correct dimensions for a perfectly square board");;
		n = numberOfSquares;
		gridMeasurement = (int) Math.sqrt(n);
	}


	@Override
	public int[] solve(int[] start, int[] goal, int maxMoves) {
		if(start.length != n && goal.length != n) throw new RuntimeException("Board size is inconsistentg with start and goal array sizes");
		this.boardSpaceArray = layoutSpaces(start);
		BoardGraph graph = new BoardGraph(boardSpaceArray);
		graph.fillNodeDistances(new Space(5,1,1));
		graph.printDistanceFills();
		
		return start;
	}
	@Override
	public int[] shortestPath(int startPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addWall(int positionI, int positionJ) {
		Space spaceI = findCorrespondingBoardSpace(positionI);
		Space spaceJ = findCorrespondingBoardSpace(positionJ);	
		Wall wall = null;
		
		if(spaceI == null && spaceJ == null){
			System.out.println("Cannot Add Wall with Null Spaces");
		} else{
			if(spaceI.isAdjacent(spaceJ)){
				if(spaceI == spaceJ.getUp()){
					wall = new Wall(spaceI, HORIZONTAL);
				} else if(spaceI == spaceJ.getDown()){
					wall = new Wall(spaceJ,HORIZONTAL);
				} else if(spaceI == spaceJ.getLeft()){
					wall = new Wall(spaceI,VERTICAL);
				} else if(spaceI == spaceJ.getRight()) {
					wall = new Wall(spaceJ,VERTICAL);
				}
			}
			
			if(wall == null){
				System.out.println("Wall cannot be placed between spaces that are not adjacent");
			} else if(wallList.contains(wall)){
				System.out.println("Wall already exists.... ignoring request");
			}else{
				placeWall(wall);
			}
		}
	}
	
	/**
	 * Returns a pointer to the wall list.
	 * @return The list of all walls at time of calling
	 */
	public LinkedList<Wall> getWallList() {
		return this.wallList;
	}
	
	public Space findCorrespondingBoardSpace(int position){
		boolean found = false;
		Space space = null;
		for(int i = 0; i < boardSpaceArray.length && !found; i++){
			if(boardSpaceArray[i].number() == position){
				space = boardSpaceArray[i];
				found = true;
			}
		}
		return space;
	}
	
	private Space[] layoutSpaces(int[] layout){
		int spaceIndex = 0;
		Space[] temp = new Space[n];
		for(int x = 0; x < gridMeasurement; x++){
			for(int y = 0; y < gridMeasurement; y++){
				temp[spaceIndex] = new Space(spaceIndex, x, y);
				temp[spaceIndex].setTile(new Tile(layout[spaceIndex]));
				spaceIndex++;
			}
		}
		return temp;
	}
	
	public Space[] getBoardSpaceArray(){
		return this.boardSpaceArray;
	}
	
	/**
	 * Tests if game has been won.
	 * @return 1 is game is won by player 1, 2 if won by player 2, 0 otherwise
	 */
	
	public int checkWin() {
		return 0;
	}
	
	public int size(){
		return n;
	}
	 

	/**
	 * Checks if a wall exists between two given spaces.
	 * @param space a, b the spaces that represent the junction being queried
	 * @return true if a wall exists between these two walls.
	 */
	public boolean wallIsHere(Space a, Space b) {
		if (!(adjacentSpaces(a, b))) {
			return false;
		}
		// swap them if out of order (a should always be above B /left of B)
		if ((b.col() < a.col()) || (b.row() < a.row())) {
			Space temp = a;
			a = b;
			b = temp;
		}
		LinkedList<Wall> toConsider = new LinkedList<Wall>();
		// obtain the walls that we need to consider in order for
		// a->b to be blocked by a wall.

		// for a,b to in same row
		if (b.equals(a.getRight())) {
			if (a.getDown() != null)
				toConsider.add(new Wall(a, true));
			if (a.getUp() != null)
				toConsider.add(new Wall(a.getUp(), true));
		}

		// for a,b in same column
		if (b.equals(a.getDown())) {
			if (a.getRight() != null)
				toConsider.add(new Wall(a, false));
			if (a.getLeft() != null)
				toConsider.add(new Wall(a.getLeft(), false));
		}
		// see if any of these walls actually exist
		while (toConsider.size() > 0) {
			if (wallList.contains(toConsider.remove())) {
				return true;
			}
		}
		return false;
	}


	
	/**
	 * Tests if space a is adjacent to space b on the board
	 * @param a
	 * @param b
	 * @return true if a and b are adjacent
	 */
	public boolean adjacentSpaces(Space a, Space b) {
		// adjacent spaces will differ by at most 1 coordinate from each other
		if ((Math.abs(a.col() - b.col()) + (Math.abs(a.row() - b.row())) == 1)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a given wall to the board (assumes valid)
	 * @param wall the wall that is to be added to the list
	 */
	public void placeWall(Wall wall) {
		wallList.add(wall);
		graph = new BoardGraph(this.getBoardSpaceArray());
	}

	/**
	 * Returns a String representation of the board's current state
	 * for printing purposes
	 */
	public String toString() {
		return BoardPrinter.buildBoardString(this);
	}

	/**
	 * Print the board layout to console
	 */
	public void print() {
		System.out.println(this);
	}

	/**
	 * Moves are passed into this function.
	 * makeMove() will check that the move is valid.
	 * If it is valid, the move is done and added to moveList.
	 * Otherwise it throws an exception.
	 * @param move
	 */
	public void makeSingleMove(Two<Space> locations) {
		if (!validMove(locations)) throw new RuntimeException("Invalid move");
			applyMove(locations);
	}

	/**
	 * Check that a move is valid with respect to the current board state.
	 * @param move
	 * @return True if the move is valid.
	 * TODO: Make it throw exceptions for invalid moves.
	 */
	public boolean validMove(Two<Space> locations) {
		if (!wallIsHere(locations._1(), locations._2()) && adjacentSpaces(locations._1(), locations._2()) && (locations._1().tileIsHole() || locations._2().tileIsHole()))
			return true;
		return false;
	}

	/**
	 * Updates the board state with the move.
	 * Assumes that the move is valid.
	 * Used for new (validated) moves or redo.
	 * @param move to be played
	 */
	private void applyMove(Two<Space> locations) {
		locations._1().swopTiles(locations._2);
	}

	/* 
	@SuppressWarnings("unchecked")
	public Board clone() {
		Board cloneBoard = new Board(this.players);
		cloneBoard.wallList = (LinkedList<Wall>) this.wallList.clone();
		cloneBoard.moveList = (LinkedList<Move>) this.moveList.clone();
		cloneBoard.moveListIndex = this.moveListIndex;
		cloneBoard.currentPlayer = this.currentPlayer;
		cloneBoard.winner = this.winner;
		cloneBoard.graph = new BoardGraph();
		for (Wall wall : wallList) {
			cloneBoard.graph.addWall(wall);
		}
		return cloneBoard;
	}
*/
	
}
