package assign11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import assign11.ReviewPredictor;

/**
 * Creates a frame to display in main method, holds buttons to execute
 * ReviewPrograms public methods and to upload a file
 * 
 * @author Madison Murray
 * @version Dec 4, 2023
 */
public class ReviewPredictorFrame extends JFrame implements ActionListener {

	// initialize all variables referenced outside the constructor
	private ReviewPredictor review;
	private JButton checkPrediction;
	private JButton predictScore;
	private JButton newFile;
	private JButton findBestWords;
	private JLabel title;
	public static JPanel contentPanel;
	public boolean goodFile = false; // current status of file uploaded, accessed by Frame
	// JTextArea bestWordsText;
	private static final long serialVersionUID = 1L;

	/**
	 * This is the constructor method of ReviewPredictorFrame class
	 */
	public ReviewPredictorFrame() {

		// exit on close
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create panel to hold all main content
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		setContentPane(contentPanel);
		contentPanel.setBackground(Color.PINK);

		// create icons to set as the buttons
		Icon check = new ImageIcon("src/assign11/predictionAcc.png");
		Icon xBest = new ImageIcon("src/assign11/findXbest.png");
		Icon scorePredict = new ImageIcon("src/assign11/predictScore.png");

		// initialize buttons with their Icons/text
		checkPrediction = new JButton(check);
		predictScore = new JButton(scorePredict);
		newFile = new JButton("Choose a File");
		findBestWords = new JButton(xBest);

		// create container for title
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		title = new JLabel("Select a valid file before performing actions.");
		titlePanel.add(title);
		titlePanel.setBackground(Color.PINK);
		title.setFont(new Font("SansSerif", Font.PLAIN, 14));
		;

		// create container to hold file upload button
		JPanel fileUploadPanel = new JPanel();
		newFile.setPreferredSize(new Dimension(200, 100));
		newFile.setBackground(Color.RED);
		newFile.setOpaque(true);
		newFile.setBorderPainted(false);
		fileUploadPanel.add(newFile);
		fileUploadPanel.setBackground(Color.PINK);
		newFile.addActionListener(this);

		// add action listener to all elements
		predictScore.addActionListener(this);
		checkPrediction.addActionListener(this);
		findBestWords.addActionListener(this);

		// add tool tips to all buttons
		predictScore.setToolTipText("Predicts the score of an entered word or review.");

		checkPrediction.setToolTipText(
				"Finds the difference between a predicted score and an actual score of an entered review/word.");
		newFile.setToolTipText("Upload a file to reference reviews from.");
		findBestWords.setToolTipText("Prints x best words- associated with the highest x reviews.");

		// add everything to content panel
		contentPanel.add(titlePanel);
		titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(predictScore);
		predictScore.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(checkPrediction);
		checkPrediction.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(findBestWords);
		findBestWords.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(fileUploadPanel);
		contentPanel.setPreferredSize(new Dimension(750, 650));

		pack();

	}

	/**
	 * Implemented method from ActionListener
	 * 
	 * @param action event source
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// get source of event
		Object eventSource = e.getSource();

		// if add file button is clicked, prompt a text box
		if (eventSource.equals(newFile)) {
			String filename = JOptionPane.showInputDialog(this, "What is the name of your file?", null);

			// try to upload a new file
			try {
				File input = new File(filename);
				review = new ReviewPredictor(input);
				goodFile = true;// update boolean status

				// catch a non existent file
			} catch (FileNotFoundException exception) {
				// alert user
				JOptionPane.showMessageDialog(this, "Your file doesnt exist.");
				title.setText("Select a valid file before performing actions.");
				goodFile = false;// update file boolean status
				return;
			}

			// catch an unformatted file
			catch (NullPointerException exception) {
				// alert user
				JOptionPane.showMessageDialog(this, "Your file is formatted incorrectly.");
				title.setText("Select a valid file before performing actions.");
				goodFile = false;// update file boolean status
				return;
			}

			// if file is good change title and newFile button design
			if (goodFile) {
				title.setText("Select an action to do with your file: " + filename);
				newFile.setText("Choose a New File");
				newFile.setBackground(Color.WHITE);

				// if file exists, but is improperly formatted
			} else {

				JOptionPane.showMessageDialog(this, "Your file doesnt exist or was incorrectly formatted.");
				title.setText("Select a valid file before performing actions.");

			}
		}

		// ONLY RUN FOLLOW METHODS IF FILE IS GOOD
		// predict score button is pressed
		if (eventSource.equals(predictScore)) {
			if (goodFile) {
				// prompt a string of text to be entered
				String reviewText = JOptionPane.showInputDialog(this, "What is the text you want to score?", null);

				// if word isnt mapped
				if (review.predictScore(reviewText) == -2) {
					JOptionPane.showMessageDialog(this, "Your word is not associated with a score.");
					return;
				}

				// if word has an average score
				if (review.predictScore(reviewText) == -1) {
					JOptionPane.showMessageDialog(this,
							"Your word is associated with an average score \nbetween 1.75 and 2.25, so it isnt included in any calculations.");
					return;
				}
				JOptionPane.showMessageDialog(this,
						"Your predicted score for " + reviewText + " is " + review.predictScore(reviewText));
			}
			// if there is no currently good uploaded file
			else {
				JOptionPane.showMessageDialog(this, "You have no uploaded file.");

			}
		}
		// if the check prediction button is pressed
		if (eventSource.equals(checkPrediction)) {
			if (goodFile) {
				// prompt a text input and a score input
				String reviewText = JOptionPane.showInputDialog(this, "Enter the text you want to score:", null);
				String actualScore = JOptionPane.showInputDialog(this, "What is the text's actual score?", null);
				double actualScoreValue = Double.parseDouble(actualScore);

				// invalid score value
				if (actualScoreValue > 4) {
					JOptionPane.showMessageDialog(this, "Your score isnt 0-4.");
					return;
				}

				// if word isnt mapped
				if (review.predictScore(reviewText) == -2) {

					JOptionPane.showMessageDialog(this, "Your word is not associated with a score.");
					return;
				}

				// if word has an average score
				if (review.predictScore(reviewText) == -1) {
					JOptionPane.showMessageDialog(this,
							"Your word is associated with an average score \nbetween 1.75 and 2.25, so it isnt included in any calculations.");
					return;
				}

				JOptionPane.showMessageDialog(this,
						"Your predicted score for " + reviewText + " is " + review.predictScore(reviewText)
								+ "\nThe difference between this and the actual score is: "
								+ review.checkPrediction(reviewText, actualScoreValue));

			}
			// if there is no good uploaded file
			else {
				JOptionPane.showMessageDialog(this, "You have no uploaded file.");
			}
		}

		// find best words button pressed
		if (eventSource.equals(findBestWords)) {
			if (goodFile) {
				// prompt a number of good buttons to be pressed
				String ranks = JOptionPane.showInputDialog(this, "How many scored words do you want to see ranked?",
						null);
				int ranksInt = Integer.parseInt(ranks);

				// create string to hold list of words
				String bestOutput = "";

				// make a list of ranked words to hold the k best words
				List<String> rankedWords = new ArrayList<String>(review.bestWords(ranksInt));

				// if k is too big of a value, let the user know it has been changed
				if (rankedWords.size() < ranksInt) {
					JOptionPane.showMessageDialog(this, "You wanted to show " + ranksInt
							+ " ranked words, \n but the file only contains " + rankedWords.size() + " mapped keys.");
				}

				// add each String in the list to bestOutput
				for (int k = 0; k < rankedWords.size(); k++) {

					bestOutput += (k + 1) + ". " + rankedWords.get(k) + "\n";
				}

				// if there are no words on the list
				if (rankedWords.size() == 0) {
					JOptionPane.showMessageDialog(this,
							"You entered an index less than or equal to zero. \nNo words shown.");
				}

				// create a frame for bestWords and replace the current visible frame
				bestWordsFrame showBest = new bestWordsFrame(bestOutput);
				showBest.setVisible(true);
				this.setVisible(false);

			}
			// for no good uploaded file
			else {
				JOptionPane.showMessageDialog(this, "You have no uploaded file.");
			}
		}

	}

	// create a frame to hold the best words displau
	private class bestWordsFrame extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;

		JButton exit;

		public bestWordsFrame(String bestWords) {

			// exit on close
			setDefaultCloseOperation(EXIT_ON_CLOSE);

			// create panel to hold content
			JPanel middlePanel = new JPanel();
			setContentPane(middlePanel);

			// add Button to exit screen
			exit = new JButton("exit");
			middlePanel.add(exit); // add to content panel
			exit.addActionListener(this); // add action listener

			// create text display area to display the list of best words
			JTextArea display = new JTextArea(50, 50);
			display.setText(bestWords);
			display.setEditable(false);
			JScrollPane scroll = new JScrollPane(display);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			// add text diplay to the panel
			middlePanel.add(scroll);

			this.pack();

		}

		/**
		 * On action performed handle event
		 * 
		 * @param Avtion event performed
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object eventSource = e.getSource();
			if (eventSource == exit) {
				this.setVisible(false);
				ReviewPredictorProgram.setFrameVisible(); // back to original ReviewPredictor frame
			}
		}
	}

}
