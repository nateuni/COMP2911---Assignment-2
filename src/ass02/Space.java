package ass02;

public class Space extends BoardComponent {
	
	private final int INNER_BOUNDS = 0;
    private final int COL;
    private final int ROW;
    private final int NUMBER;
    private Tile tile;

	/**
     * @param COL
     * @param ROW
     * @exception throws a RuntimeException if the space is out of bounds
     */
    public Space(int number, int row, int col) {
    	this.COL = col;
    	this.ROW = row;
    	this.NUMBER = number;
    }
  
    public int row(){
    	return this.ROW;
    }
    
    public int col(){
    	return this.COL;
    }
    
    public int number(){
    	return this.NUMBER;
    }
    
    public Tile getTile(){
    	return tile;
    }
    
    public void setTile(Tile newTile){
    	this.tile = newTile;
    }
    
    public boolean tileIsHole() {
		if(this.getTile().isHole()) return true;
		else return false;
	}
    
    public int bounds(){
    	return gridMeasurement;
    }

    
	/*
     * obtain the space above
     * @return space above this object
     */
    public Space getUp() {
        if (ROW == 0) {
            return null;
        }
        return new Space(this.number() - gridMeasurement, this.ROW - 1, this.COL);
    }

    /*
     * obtain the space below
     * @return space below this object
     */
    public Space getDown() {
        if (ROW == this.bounds()) {
            return null;
        }
        return new Space(this.number() + gridMeasurement, this.ROW + 1, this.COL);
    }

    /*
     * obtain the space to the left
     * @return space to left of this object
     */
    public Space getLeft() {
        if (COL == 0) {
            return null;
        }
        return new Space(this.number() - 1, this.ROW, this.COL - 1);
    }

    /*
     * obtain the space to the right
     * @return space to right of this object
     */
    public Space getRight() {
        if (COL == this.bounds()) {
            return null;
        }
        return new Space(this.number() + 1, this.ROW, this.COL + 1);
    }
    
   
     

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Space)) {
            return false;
        }
        Space otherSpace = (Space) obj;
        return (this.col() == otherSpace.col() && this.row() == otherSpace.row() && this.number() == otherSpace.number());
    }
    
    @Override
	public String toString() {
		return "Space "+Integer.toString(this.number())+": "+"["+this.row()+","+this.col()+"]";
	}
		
	 /**
     * Checks the space is within the bounds of the board
     * @return The result.
     */
    boolean withinBounds(int number) {
    	return (number >= INNER_BOUNDS && number < this.gridMeasurement());
    }

    public boolean isAdjacent(Object obj) {
    	if(!(obj instanceof Space)) {
    		return false;
    	}
    	Space other = (Space) obj;
    	return (Math.abs(this.row() - other.row()) == 1 && Math.abs(this.col() - other.col()) == 0 || Math.abs(this.row() - other.row()) == 0 && Math.abs(this.col() - other.col()) == 1);
	}
    
    public boolean swopTiles(Space other){
    	if(isAdjacent(other) && this.tileIsHole() || other.tileIsHole()){
    		Tile temp = this.getTile();
        	this.setTile(other.getTile());
        	other.setTile(temp);
        	return true;
    	}
    	return false;
    }
}
