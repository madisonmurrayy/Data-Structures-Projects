package assign11;

import javax.swing.JFrame;

/**
 * This class contains the main method to run the GUI and make the first frame
 * visible
 * 
 * @author Madison Murray
 * @version Dec 4 2023
 */
public class ReviewPredictorProgram {

	static ReviewPredictorFrame frame = new ReviewPredictorFrame();

	/**
	 * Main method sets the frame visible
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setFrameVisible();
	}

	/**
	 * Static method to be called from BestWordsFrame in order to make this frame
	 * visible again
	 */
	public static void setFrameVisible() {
		frame.setVisible(true);
	}

}
