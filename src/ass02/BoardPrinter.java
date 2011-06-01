package ass02;

public class BoardPrinter {

	static final String V_WALL = "|";
	static final String H_WALL = "=";
	static final String V_DIVIDER = ":";
	static final String H_DIVIDER_0 = "... ";
	static final String H_DIVIDER_1 = "---";
	static final String H_DIVIDER_2 = " - ";
	static final String CORNER_0 = " ";
	static final String CORNER_1 = "+";
	
	static int style = 0;

	public static void setStyle(int newStyle) {
		if (newStyle >=0 && newStyle <= 2) style = newStyle;
		else throw new RuntimeException("Invalid style");
	}

	public static void setStyle(String newStyle) {
		setStyle(Integer.parseInt(newStyle) - 1);
	}
	
	private static String getHDivider() {
		if (style == 1) return H_DIVIDER_1;
		if (style == 2) return H_DIVIDER_2;
		return H_DIVIDER_0;
	}

	private static String getCorner() {
		if (style == 0) return CORNER_0;
		return CORNER_1;
	}

	public static String buildBoardString(Board board) {
		StringBuilder boardString = new StringBuilder();
		int i, row, col, rowNum = 0;
		
		boardString.append("   ");
		for(i = 0; i < board.gridSize(); i++){
			boardString.append("---- ");
		}
		boardString.append("\n");

		String hDivider = getHDivider();
		String corner = getCorner();
		
		// Create array for walls
		int [][] wallArray = new int [board.size()][board.size()];
		for (row = 0; row < board.gridSize(); row++) {
			for (col = 0; col < board.gridSize(); col++) {
				wallArray[row][col] = 0;
			}
		}
		
		/*
		// Fill array with wall locations
		for (Wall wall : board.getWallList()) {
			row = wall.getSpace().row() - 1;
			col = wall.getSpace().col() - 1;
			if (wall.isVertical()) wallArray[row][col] = 1;
			else wallArray[row][col] = 2;
		}
		*/

		i = 0;
		for (row = 0; row < board.gridSize(); row++) {
			// Print row of spaces
			boardString.append((rowNum+1)+" |");
			rowNum++;
			for (col = 0; col < board.gridSize(); col++) {
				if(board.getSpaceArray()[i].getTile() >= 10){
					boardString.append(" "+board.getSpaceArray()[i].getTile()+" ");
				} else {
					boardString.append("  "+board.getSpaceArray()[i].getTile()+" ");
				}
				
				i++;
				if (col != board.gridSize() - 1) {
					if (wallArray[row][col] == 1 || row != 0 && wallArray[row-1][col] == 1) boardString.append(V_WALL);
					else boardString.append(V_DIVIDER);
				}
			}
			boardString.append(" |\n");
			
			// Print divider between rows
			if (row != board.gridSize() - 1) {
				boardString.append("  | ");
				for (col = 0; col < board.gridSize(); col++) {
					if (wallArray[row][col] == 2 || col != 0 && wallArray[row][col-1] == 2) boardString.append(H_WALL + H_WALL + H_WALL);
					else boardString.append(hDivider);
					
					if (col != board.gridSize() - 1) {
						if (wallArray[row][col] == 1) boardString.append(V_WALL);
						else if (wallArray[row][col] == 2) boardString.append(H_WALL);
						else boardString.append(corner);
					}
				}
				boardString.append("| \n");
			}
		}
		
		boardString.append("   ");
		for(i = 0; i < board.gridSize(); i++){
			boardString.append("---- ");
		}
		boardString.append("   \n    ");
		for(i = 0; i < board.gridSize(); i++){
			boardString.append(" "+(i+1)+"   ");
		}		
		return boardString.toString();
	}
	
}
