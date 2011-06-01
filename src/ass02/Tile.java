package ass02;

public class Tile {
	
	private final int NUMBER;
	private final int BOARD_SIZE;
	
	public Tile(int number, int boardSize){
		BOARD_SIZE = boardSize;
		 if(number >= BOARD_SIZE){
	        	throw new RuntimeException("Invalid Tile");
	        } else {
	        	this.NUMBER = number;
	        }
	}
	
	public int number(){
		return NUMBER;
	}
}
