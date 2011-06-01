package ass02;

import Position;

public class Space {
	
	private static int OUTER_BOUNDS;
	private final int INNER_BOUNDS = 0;
    private final int COL;
    private final int ROW;
    private final int NUMBER;
    private final int gridMeasurement;
    private int tile;

	/**
     * @param COL
     * @param ROW
     * @exception throws a RuntimeException if the space is out of bounds
     */
    public Space(int number, int boardSize, int col, int row, int tileNumber) {
    	OUTER_BOUNDS = boardSize;
        if(!withinBounds(number)){
        	throw new RuntimeException("Space is not within bounds of the board");
        } else {
        	this.NUMBER = number;
        	this.gridMeasurement = (int) Math.sqrt(this.bounds());
        	this.COL = col;
        	this.ROW = row;
        	this.tile = tileNumber;
        }
    }
    
    public int number() {
		return NUMBER;
	}
    
    public int bounds() {
		return OUTER_BOUNDS;
	}
    
    public int row(){
    	return this.ROW;
    }
    
    public int col(){
    	return this.COL;
    }
    
    public int getTile(){
    	return tile;
    }

    
	/*
     * obtain the space above
     * @return space above this object
     
    public Space getUp() {
        if (ROW == 1) {
            return null;
        }
        return new Space((COL - nByN), this.bounds(), COL, ROW - 1);
    }

    
     * obtain the space below
     * @return space below this object
     
    public Space getDown() {
        if (ROW == this.bounds()) {
            return null;
        }
        return new Space((COL - nByN), this.bounds(), COL, ROW + 1);
    }

    /*
     * obtain the space to the left
     * @return space to left of this object
     
    public Space getLeft() {
        if (COL == 0) {
            return null;
        }
        return new Space((COL - nByN), this.bounds(), COL - 1, ROW);
    }

    /*
     * obtain the space to the right
     * @return space to right of this object
     
    public Space getRight() {
        if (COL == this.bounds()) {
            return null;
        }
        return new Space((COL - nByN), this.bounds(), COL + 1, ROW);
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
        return (this.number() == otherSpace.number() && this.bounds() == otherSpace.bounds() && this.col() == otherSpace.col() && this.row() == otherSpace.row() && this.getTile() == otherSpace.getTile());
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
    	return (number >= INNER_BOUNDS && number < OUTER_BOUNDS);
    }

    public boolean isAdjacent(Object obj) {
    	if(!(obj instanceof Space)) {
    		return false;
    	}
    	Space pos = (Space) obj;
    	return (Math.abs(this.row() - pos.row()) == 1 && Math.abs(this.col() - pos.col()) == 0 || Math.abs(this.row() - pos.row()) == 0 && Math.abs(this.col() - pos.col()) == 1);
	}
}
