package ass02;

public class Node {
	
	private boolean visited = false;
	private Node parent = null;
	private int f;
	private int g;
	private int h;
	private Space space = null;

	
	
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
		Node n = (Node) obj;
		return this.pos.equals(n.pos);
	}	
}
