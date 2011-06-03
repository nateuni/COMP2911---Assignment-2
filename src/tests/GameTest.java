package tests;

//import static org.junit.Assert.assertFalse;
import ass02.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.junit.Assert;


public class GameTest {
	
	SlidingBlock sb;
	private final int[] start = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};

	@Before
	public void setUp() throws Exception {
		int n = 25;
		sb = Factory.make(n);
	}

	@Test
	public void test1(){
		sb.solve(start, start, 20);
		sb.addWall(0,1);
		sb.addWall(0,5);
		System.out.print(sb.toString());
	}
	
	
	@After
	public void tearDown() throws Exception {
	}
	
	

}
