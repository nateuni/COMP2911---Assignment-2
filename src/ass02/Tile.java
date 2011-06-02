package ass02;

public class Tile {
	
	private final int NUMBER;
	
	public Tile(int number){
		NUMBER = number;
	}
	
	public int number(){
		return NUMBER;
	}
	
	public boolean isHole() {
		if(NUMBER == 0) return true;
		else return false;
	}
}
