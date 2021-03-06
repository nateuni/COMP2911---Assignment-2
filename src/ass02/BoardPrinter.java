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
		StringBuilder boardString = new StringBuilder();board.gridMeasurement();
		int i, row, col, rowNum = 0;
		
		boardString.append("   ");
		for(i = 0; i < board.gridMeasurement(); i++){
			boardString.append("---- ");
		}
		boardString.append("\n");

		String hDivider = getHDivider();
		String corner = getCorner();
		
		// Create array for walls
		int [][] wallArray = new int [board.gridMeasurement()][board.gridMeasurement()];
		for (row = 0; row < board.gridMeasurement(); row++) {
			for (col = 0; col < board.gridMeasurement(); col++) {
				wallArray[row][col] = 0;
			}
		}
		
		
		// Fill array with wall locations
		for (Wall wall : board.getWallList()) {
			row = wall.getSpace().row();
			col = wall.getSpace().col();
			if (wall.isVertical() && wallArray[row][col] == 0) wallArray[row][col] = 1;
			else if (wall.isHorizontal() && wallArray[row][col] == 0) wallArray[row][col] = 2;
			else if (wallArray[row][col] == 1 || wallArray[row][col] == 2) wallArray[row][col] = 3;
		}
		

		i = 0;
		for (row = 0; row < board.gridMeasurement(); row++) {
			// Print row of spaces
			boardString.append((rowNum)+" |");
			rowNum++;
			for (col = 0; col < board.gridMeasurement(); col++) {
				if(board.getBoardSpaceArray()[i].getTile().number() >= 10){
					boardString.append(" "+board.getBoardSpaceArray()[i].getTile().number()+" ");
				} else {
					boardString.append("  "+board.getBoardSpaceArray()[i].getTile().number()+" ");
				}
				
				i++;
				if (col != board.gridMeasurement() - 1) {
					if (wallArray[row][col] == 1 || wallArray[row][col] == 3) boardString.append(V_WALL);
					else boardString.append(V_DIVIDER);
				}
			}
			boardString.append(" |\n");
			
			// Print divider between rows
			if (row != board.gridMeasurement() - 1) {
				boardString.append("  | ");
				for (col = 0; col < board.gridMeasurement(); col++) {
					if (wallArray[row][col] == 2 || wallArray[row][col] == 3) boardString.append(H_WALL+H_WALL);
					else boardString.append(hDivider);
					
					if (col != board.gridMeasurement() - 1) {
						if (wallArray[row][col] == 2 || wallArray[row][col] == 3) boardString.append(H_WALL+"  ");
						else boardString.append(corner);
					}
				}
				boardString.append("| \n");
			}
		}
		
		boardString.append("   ");
		for(i = 0; i < board.gridMeasurement(); i++){
			boardString.append("---- ");
		}
		boardString.append("   \n    ");
		for(i = 0; i < board.gridMeasurement(); i++){
			boardString.append(" "+(i)+"   ");
		}		
		return boardString.toString();
	}
	
}
