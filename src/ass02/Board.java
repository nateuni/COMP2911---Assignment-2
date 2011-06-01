package ass02;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A board has Two Players and up to 20 placed walls (10 per player) and tracks each of their coordinates.
 * @author Team Stump
 */
public class Board implements SlidingBlock {


	protected LinkedList<Wall> wallList = new LinkedList<Wall>();
	/*
	protected LinkedList<Move> moveList = new LinkedList<Move>();
	protected int moveListIndex = -1;
	public BoardGraph graph = new BoardGraph();
	*/
	
	private final int size;
	private final int gridMeasurement;
	//private Space start;
	//private Space goal;
	//private int maxMoves;
	private Space[] startSpaceArray;
	private Space[] goalSpaceArray;
	
	
	public Board(int n){
		if(n < 4) throw new RuntimeException("Minimum of 4 spaces on a board");
		this.size = n;
		this.gridMeasurement = (int) Math.sqrt(this.size);
	}


	@Override
	public int[] solve(int[] start, int[] goal, int maxMoves) {
		if(start.length != size && goal.length != size) throw new RuntimeException("Board size is inconsistentg with start and goal array sizes");
		this.startSpaceArray = layoutSpaces(start);
		this.goalSpaceArray = layoutSpaces(goal);
		return start;
	}
	@Override
	public int[] shortestPath(int startPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addWall(int positionI, int positionJ) {
		
	}
	
	private Space[] layoutSpaces(int[] layout){
		int spaceIndex = 0;
		Space[] temp = new Space[this.size];
		for(int x = 0; x < gridMeasurement; x++){
			for(int y = 0; y < gridMeasurement; y++){
				temp[spaceIndex] = new Space(spaceIndex, size, y, x, layout[spaceIndex]);
				spaceIndex++;
			}
		}
		return temp;
	}
	
	public Space[] getSpaceArray(){
		return this.startSpaceArray;
	}
	
	/**
	 * Tests if game has been won.
	 * @return 1 is game is won by player 1, 2 if won by player 2, 0 otherwise
	 */
	
	public int checkWin() {
		return 0;
	}
	
	public int size(){
		return this.size;
	}
	
	public int gridSize(){
		return this.gridMeasurement;
	}
	
	/**
	 * 
	 * @param 
	 
	private boolean spaceHasTile(Space space) {
		if(space.getTile() == 0) return false;
		else return false;
	}

	/**
	 * Checks if a wall exists between two given spaces.
	 * @param space a, b the spaces that represent the junction being queried
	 * @return true if a wall exists between these two walls.
	 
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
	 
	public void addWall(Wall wall) {
		wallList.add(wall);
		graph.addWall(wall);
		currentPlayer.decrementWallTally();
	}*/

	/**
	 * Returns a String representation of the board's current state
	 * for printing purposes
	 */
	public String toString() {
		return BoardPrinter.buildBoardString(this);
	}

	/**
	 * Print the board layout to console
	 
	public void print() {
		System.out.println(this);
	}

	/**
	 * Moves are passed into this function.
	 * makeMove() will check that the move is valid.
	 * If it is valid, the move is done and added to moveList.
	 * Otherwise it throws an exception.
	 * @param move
	 *
	public void makeMove(Move move) {
		if (!moveValid(move)) throw new RuntimeException("Invalid move");
		if (moveList.size() > 0) {
			assert (moveListIndex >= 0);
			while (moveList.size() > moveListIndex + 1)
				moveList.removeLast();
		}
		move.owner = currentPlayer;
		moveList.add(move);
		moveListIndex++;
		applyMove(move);
	}

	/**
	 * Check that a move is valid with respect to the current board state.
	 * @param move
	 * @return True if the move is valid.
	 * TODO: Make it throw exceptions for invalid moves.
	 *
	public boolean moveValid (Move move) {
		if (move instanceof MovementMove) {
			MovementMove mMove = (MovementMove) move;
			if (!wallIsHere(mMove.from(), mMove.to()) 
					&& mMove.from().equals(currentPlayer.getSpace()) 
					&& !isOccupied(mMove.to()) 
					&& (adjacentSpaces(mMove.from(), mMove.to()) || jumpValid(mMove))) {
				return true;
			}
			return false;
		}	
		if (move instanceof WallMove) {
			if(!currentPlayer.hasWallsLeft()) {
				return false;
			}
			WallMove wMove = (WallMove) move;
			Wall proposedWall = wMove.wall();
			if (wallList.contains(new Wall(wMove.wall().getSpace(), !wMove.wall().isVertical()))) return false;
			if (proposedWall.isHorizontal()
					&&!wallIsHere(proposedWall.getSpace(), proposedWall.getSpace().getDown())
					&&!wallIsHere(proposedWall.getSpace().getRight(), proposedWall.getSpace().getDown().getRight())) {
				if (cutsOffPath((WallMove) move)) return false;
				return true;
			}
			else if (!proposedWall.isHorizontal()
					&&!wallIsHere(proposedWall.getSpace(), proposedWall.getSpace().getRight())
					&&!wallIsHere(proposedWall.getSpace().getDown(), proposedWall.getSpace().getRight().getDown())) {
				if (cutsOffPath((WallMove) move)) return false;
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates the board state with the move.
	 * Assumes that the move is valid.
	 * Used for new (validated) moves or redo.
	 * @param move to be played
	 *
	private void applyMove(Move move) {
		if (move instanceof MovementMove) {
			move.owner.setSpace(((MovementMove) move).to());
		}
		if (move instanceof WallMove) {
			this.addWall(((WallMove) move).wall());
		}
		currentPlayer = players.other(currentPlayer);
	}

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
