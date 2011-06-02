package ass02;


/**
 * @author Stump
 * A wall can be either vertical or horizontal as it's location is defined by a space. 
 */
public class Wall extends BoardComponent{
	private Space space;
	private Boolean vertical;

	/**
	 * Sets the type and coordinates of the wall. 
	 * @param space  
	 * @param vertical If the wall is vertical or horizontal as defined by a boolean.
	 * @exception 
	 */
	public Wall(Space space, Boolean vertical) {
		if(!validWallSpace(space)){
			throw new RuntimeException("Not a valid Wall");
		} else {
			this.space = space;
			this.vertical = vertical;
		}
	}

	/**
	 * @return The space from which the wall is located.
	 */
	public Space getSpace() {
		return space;
	}

	/**
	 * Checks if it is a valid wall space and returns the result as boolean
	 * @param space the space that is being checked. 
	 * @return the result 
	 */
	private boolean validWallSpace(Space space) {
		return !(space.col() >= this.gridMeasurement() || space.row() >= this.gridMeasurement());
	}

	/**
	 * @return If the wall is vertical or not.
	 */
	public Boolean isVertical() {
		return vertical;
	}

	/**
	 * @return If the wall is horizontal or not.
	 */
	public Boolean isHorizontal() {
		return !vertical;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Wall)) return false;
		Wall otherWall = (Wall) obj;
		return (this.getSpace().equals(otherWall.getSpace()) && this.isVertical() == otherWall.isVertical());
	}
	
	@Override
	public String toString(){
		if(this.isVertical()){
			return "VWall: ["+this.getSpace().row()+","+this.getSpace().col()+"]";
		}
		return "HWall: ["+this.getSpace().row()+","+this.getSpace().col()+"]";
	}
}

/**

public class Wall {
	private Space space;
	
	
	
	 * Sets the type and coordinates of the wall. 
	 * @param space  
	 * @param vertical If the wall is vertical or horizontal as defined by a boolean.
	 * @exception 
	 *
	public Wall(int boardSize, int firstSpace, int secondSpace) {
		OUTER_BOUNDS = boardSize;
		if(invalidWallSpace(firstSpace, secondSpace)){
			throw new RuntimeException("Not a valid Wall");
		} else {
			this.firstSpace = firstSpace;
			this.secondSpace = secondSpace;
		}
	}

	**
	 * @return The space from which the wall is located.
	 *
	public int[] getPosition() {
		int [] position = {this.firstSpace, this.secondSpace};
		return position;
	}
	
	private boolean invalidWallSpace(int firstSpace, int secondSpace) {
		return !(firstSpace >= INNER_BOUNDS && firstSpace < OUTER_BOUNDS && secondSpace >= INNER_BOUNDS && secondSpace < OUTER_BOUNDS);
	}
	
	* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 *
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
	
}*/
