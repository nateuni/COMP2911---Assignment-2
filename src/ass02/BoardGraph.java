package ass02;

import java.util.ArrayList;
import java.util.List;

/**
 * A graph structure representing the spaces on the board.
 * Used for finding distances to the exits and checking if a wall move
 * would completely block a player's path.
 */

public class BoardGraph extends BoardComponent {
	protected Node [][] node;

	/**
	 * Constructs a BoardGraph for a new board with no walls.
	 */
	public BoardGraph(Space[] spaceArray) {
		gridMeasurement = (int) Math.sqrt(n);
		int row, col, i = 0;
		node = new Node [gridMeasurement][gridMeasurement];
		for (row = 0; row < gridMeasurement; row++) {
			for (col = 0; col < gridMeasurement; col++) {
				node[row][col] = new Node(spaceArray[i++]);
			}
		}
		for (row = 0; row < gridMeasurement; row++) {
			for (col = 0; col < gridMeasurement; col++) {
				if (row == 0) node[row][col].up = null;
				else node[row][col].up = node[row-1][col];
				if (row == gridMeasurement - 1) node[row][col].down = null;
				else node[row][col].down = node[row+1][col];
				if (col == 0) node[row][col].left = null;
				else node[row][col].left = node[row][col-1];
				if (col == gridMeasurement - 1) node[row][col].right = null;
				else node[row][col].right = node[row][col+1];
			}
		}
	}

	/**
	 * Removes edges between nodes that have been separated by a new wall.
	 * @param wall The wall that has been added.
	 */
	public void addWall(Wall wall) {
		int row = wall.getSpace().row();
		int col = wall.getSpace().col();
		assert(row >= 0 && row < n && col >= 0 && col < n);
		if (wall.isVertical()) {
			node[row][col].right = null;
			node[row+1][col].right = null;
			node[row][col+1].left = null;
			node[row+1][col+1].left = null;
		}
		else {
			node[row][col].down = null;
			node[row][col+1].down = null;
			node[row+1][col].up = null;
			node[row+1][col+1].up = null;
		}
	}

	/**
	 * Performs a breadth-first search across the graph, filling in each node's
	 * distanceToExit attribute with its shortest distance to one of the exit nodes.
	 * This is a player's least possible distance to get to its exit row, not considering
	 * jump moves or additional wall placements.
	 * This must be done for a single player before examining individual spaces with getDist().
	 * @param exits A list of target spaces which make up the player's exit row
	 */
	@SuppressWarnings("unchecked")
	public void fillNodeDistances(Space goal) {
		ArrayList<Node> currentNodes = new ArrayList<Node>();
		ArrayList<Node> nextNodes = new ArrayList<Node>();
		Node checkNode;
		int row, col;
		for (row = 0; row < gridMeasurement; row++) {
			for (col = 0; col < gridMeasurement; col++) {
				node[row][col].distanceToGoal = -1;
			}
		}
			node[goal.row()][goal.col()].distanceToGoal = 0;
			currentNodes.add(node[goal.row()][goal.col()]);

		while (currentNodes.size() > 0) {
			nextNodes.clear();
			for (Node thisNode : currentNodes) {
				checkNode = tryNode(thisNode.up, thisNode.distanceToGoal);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.right, thisNode.distanceToGoal);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.down, thisNode.distanceToGoal);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
				checkNode = tryNode(thisNode.left, thisNode.distanceToGoal);
				if (checkNode != null && !nextNodes.contains(checkNode)) nextNodes.add(checkNode);
			}
			currentNodes = (ArrayList<Node>) nextNodes.clone();
		}
	}

	/**
	 * Checks whether the search so far has found a shorter path to a node
	 * than a previously found path.
	 * @param thisNode The node to be checked.
	 * @param distance The distance traveled in the new path.
	 * @return The node if a new path was found, otherwise null
	 */
	private Node tryNode(Node thisNode, int distance) {
		if (thisNode == null) return null;
		if (thisNode.distanceToGoal != -1 && distance + 1 >= thisNode.distanceToGoal) return null;
		thisNode.distanceToGoal = distance + 1;
		return thisNode;
	}

	/**
	 * Prints a grid of the distances found from the most recent call
	 * to fillNodeDistances().
	 * Only used for testing.
	 */
	public void printDistanceFills() {
		System.out.println("BoardGraph Distance Fills:\n");
		int row, col;
		for (row = 0; row < gridMeasurement; row++) {
			System.out.print(" ");
			for (col = 0; col < gridMeasurement; col++) {
				System.out.printf("%4d", node[row][col].distanceToGoal);
			}
			System.out.println();
		}
	}

	/**
	 * Gets the distanceToExit value for a particular space, as found from
	 * the most recent call to fillNodeDistances().
	 * @param space
	 * @return
	 */
	public int getDist(Space space) {
		return node[space.row()][space.col()].distanceToGoal;
	}
}
