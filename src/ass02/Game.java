package ass02;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] start = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		SlidingBlock sb = Factory.make(16);
		sb.solve(start, start, 1);
		Board b = (Board) sb;
		System.out.println(b.toString());

	}

}
