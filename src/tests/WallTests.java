package tests;
import ass02.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTests {
	
	Board board = new Board(9);
	Wall wall1 = new Wall(new Space(0,0,0), true);
	Wall wall2 = new Wall(new Space(0,0,0), true);
	Wall wall3 = new Wall(new Space(1,1,0), true);
	
	@Test
	public void checkWallsForEquality(){
		assertTrue(wall1.equals(wall2));
	}
	
	@Test
	public void checkWallsForInequality(){
		assertFalse(wall1.equals(wall3));
	}
	
	@Test
	public void printWallLocation(){
		System.out.println(wall1.toString());
		System.out.println(wall2.toString());
		System.out.println(wall3.toString());
	}
	
	@Test
	(expected=RuntimeException.class) public void cannotMakeOutOfBoundsWall(){
		new Wall(new Space(9,9,0), true);
	}
}
