package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the SlidePuzzleFrame class.
 * 
 * @author Prof. Heisler and Madison Murray
 * @version Nov 13 2023
 */
public class SlidePuzzleFrameTest {  
	
	
	@Test
	public void testStartInUnsolvedState() { 
		SlidePuzzleFrame frame = new SlidePuzzleFrame();
		assertFalse(frame.isSolved());
	}
	
	
	@Test
	public void testShuffle() {
		SlidePuzzleFrame frame = new SlidePuzzleFrame();
		frame.solve();
		frame.shuffle();
		assertFalse(frame.isSolved());
	}
	
	
	@Test
	public void testSolveAfterShuffle() {
		SlidePuzzleFrame frame = new SlidePuzzleFrame();
		frame.shuffle();
		frame.solve();
		assertTrue(frame.isSolved());
	}
	
	
	@Test
	public void testSolve() {
		SlidePuzzleFrame frame = new SlidePuzzleFrame();
		frame.solve();
		assertTrue(frame.isSolved());
	}
	

		
}