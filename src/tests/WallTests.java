package tests;
import ass02.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTests {
	
	Wall wall1 = new Wall(9, 0, 1);
	Wall wall2 = new Wall(9, 0, 1);
	Wall wall3 = new Wall(9, 0, 4);
	
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
		new Wall(9, 0, 9);
	}
}
