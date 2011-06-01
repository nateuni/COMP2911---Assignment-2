package ass02;

/**
 */
public class Wall {
	
	
	private final int firstSpace;
	private final int secondSpace;
	private final int INNER_BOUNDS = 0;
	private final int OUTER_BOUNDS;
	
	/**
	 * Sets the type and coordinates of the wall. 
	 * @param space  
	 * @param vertical If the wall is vertical or horizontal as defined by a boolean.
	 * @exception 
	 */
	public Wall(int boardSize, int firstSpace, int secondSpace) {
		OUTER_BOUNDS = boardSize;
		if(invalidWallSpace(firstSpace, secondSpace)){
			throw new RuntimeException("Not a valid Wall");
		} else {
			this.firstSpace = firstSpace;
			this.secondSpace = secondSpace;
		}
	}

	/**
	 * @return The space from which the wall is located.
	 */
	public int[] getPosition() {
		int [] position = {this.firstSpace, this.secondSpace};
		return position;
	}
	
	private boolean invalidWallSpace(int firstSpace, int secondSpace) {
		return !(firstSpace >= INNER_BOUNDS && firstSpace < OUTER_BOUNDS && secondSpace >= INNER_BOUNDS && secondSpace < OUTER_BOUNDS);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Wall)) return false;
		Wall otherWall = (Wall) obj;
		int[] thisWallPos = this.getPosition();
		int[] otherWallPos = otherWall.getPosition();
		return (thisWallPos[0] == otherWallPos[0] && thisWallPos[1] == otherWallPos[1] && this.OUTER_BOUNDS == otherWall.OUTER_BOUNDS);
	}
	
	@Override
	public String toString(){
		int[] pos = this.getPosition();
		return "Wall: ["+pos[0]+","+pos[1]+"]";
	}
	
	public void isAdjacent(){
		
	}
	
}
