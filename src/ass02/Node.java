package ass02;

import java.util.ArrayList;

public class Node {

	public Space space;
	public Node up;
	public Node right;
	public Node down;
	public Node left;
	public ArrayList<Node> path;
	public int distanceToGoal;

	/**
	 * Node constructor. 
	 * @param space The passed space from the board. 
	 */
	public Node(Space space) {
		this.space = space;
		up = null;
		right = null;
		down = null;
		left = null;
		path = new ArrayList<Node>();
		distanceToGoal = -1;
	}
	
	/*	
	private boolean visited = false;
	private Node parent = null;
	private int f;
	private int g;
	private int h;
	private Space space = null;
	
	
	public Node(Space space){
		this.space = space;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Node)) {
			return false;
		}
		Node other = (Node) obj;
		return this.space.equals(other.space); 
	}
	*/	
}
