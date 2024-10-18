package assign09;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * This class creates the frame for a slide puzzle of 16 photos of tileButton objects - 
 * solved by swapping adjacent tiles, this class is
 * implementing methods to solve and shuffle the puzzle, as well as
 * check if the puzzle is solved
 * 
 * @author Madison Murray
 * @version Nov 13 2023
 */
public class SlidePuzzleFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private TileButton[][] buttonGrid; // holds 16 tile buttons to create puzzle
	private TileButton emptyTile; // to be initially assigned to space 0,0
	private JButton shuffle = new JButton("Shuffle"); // button to call shuffle method
	private JButton solution = new JButton("Show Solution"); // button to call solve method

	/**
	 * Creates a SlidePuzzleFrame object, initializing buttonGrid and creating
	 * panels to hold the 4x4 grid, the buttons, and the title
	 */
	public SlidePuzzleFrame() {

		// exit on close
		buttonGrid = new TileButton[4][4];
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create a container to hold and organize the 16 buttons
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(4, 4));
		gridPanel.setPreferredSize(new Dimension(720, 720));

		// Construct 16 TileButtons by passing in name of file and row/column
		// into buttonGrid[][]
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {

				buttonGrid[row][col] = new TileButton(("src/assign09/tile_" + row + "_" + col) + ".png", row, col,
						(row * 4 + col));
				gridPanel.add(buttonGrid[row][col]);
				buttonGrid[row][col].addActionListener(this);
			}
		}

		// initialize emptyTile as the first tile in the grid
		emptyTile = buttonGrid[0][0];

		// add action listener to button objects
		shuffle.addActionListener(this);
		solution.addActionListener(this);

		// create a container to hold the title and instructions
		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Slide Puzzle! Click tiles adjacent to the empty tile to solve.");
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(title);

		// add a panel to contain shuffle and show solution buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(shuffle);
		buttonPanel.add(solution);

		// create a fourth panel to hold the first three panels
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(titlePanel, BorderLayout.NORTH);
		outerPanel.add(gridPanel, BorderLayout.CENTER);
		outerPanel.add(buttonPanel, BorderLayout.PAGE_END);

		// set the jframe
		setContentPane(outerPanel);
		shuffle.addActionListener(this);
		solution.addActionListener(this);

		// shuffle tiles upon opening window
		shuffle();

		pack();
	}

	/**
	 * This method checks if the tile clicked is adjacent to the location of the
	 * current emptyTile
	 * 
	 * @param tile that was clicked
	 * @return true if this tile is adjacent to empty tile, false if not
	 */
	private boolean adjacentToEmpty(TileButton tile) {
		
		// check if distance between this tile and other tile are 1
		if (Math.abs(tile.getRow() - emptyTile.getRow()) + Math.abs(tile.getColumn() - emptyTile.getColumn()) == 1) {

			return true;
		}
		return false;

	}

	/**
	 * This method repeatedly generates a random tile, and swaps with the emptyTile
	 * if and only if they are adjacent. This repeats 100 successful loops, until
	 * puzzle is sufficiently shuffled.
	 */
	public void shuffle() {

		int count = 0; // counts number of swaps that have been made

		// while there have been less than 100 swaps
		while (count < 100) {

			// generate random tile
			Random rng = new Random();
			TileButton random = buttonGrid[rng.nextInt(4)][rng.nextInt(4)];

			// if tile is adjacent to empty, swap them
			if (adjacentToEmpty(random) == true) {
				emptyTile.swap(random);

				count++; // increase swap count

				emptyTile = buttonGrid[random.getRow()][random.getColumn()];// update empty tile's location
			}
		}
	}

	/**
	 * This method finds the tileButton that belongs at current IDindex, and swaps
	 * with the current incorrect tile at IDindex. This is repeated for all tiles in
	 * the gird until the puzzle is solved
	 */
	public void solve() {

		// loop through each ID in the 4x4 grid
		for (int IDindex = 0; IDindex < 16; IDindex++) {

			// loops through every button until the correct one is found
			for (int row = 0; row < buttonGrid.length; row++) {
				for (int col = 0; col < buttonGrid[0].length; col++) {

					// if the button belongs at IDindex, swap it
					if (buttonGrid[row][col].getImageID() == IDindex) {
						buttonGrid[row][col].swap(buttonGrid[IDindex / 4][IDindex % 4]);
					}
				}
			}
		}
		
		// reset empty tile
		emptyTile = buttonGrid[0][0];
	}

	/**
	 * This method checks to see if every tileButton is at the correct ID(is solved)
	 * 
	 * @return true if puzzle is solved
	 */
	public boolean isSolved() {

		// loops through every tileButton
		for (int row = 0; row < buttonGrid.length; row++) {
			for (int col = 0; col < buttonGrid.length; col++) {

				// if the id does NOT match the placement
				if (!(buttonGrid[row][col].getImageID() == (row * 4 + col))) {

					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method implements the Action Listener method actionPerformed, shuffling
	 * the puzzle if shuffle button is clicked, solving the puzzle if solve button
	 * is clicked, swapping adjacent tiles if a tileButton is clicked Shows messages
	 * congratulating a solved puzzle, and an error message if a non adjacent tile
	 * is clicked
	 */
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		// if shuffle button is clicked, shuffle
		if (eventSource.equals(shuffle)) {
			shuffle();
		}
		// if show solution button is clicked, call solve()
		else if (eventSource.equals(solution)) {
			solve();
		}
		// if a tile button is clicked, check if adjacent
		else if (eventSource instanceof TileButton) {

			TileButton reference = (TileButton) eventSource;

			// if adjacent to empty, swap the tiles
			if (adjacentToEmpty(reference)) {
				reference.swap(emptyTile);
				emptyTile = reference;

				if (isSolved()) { // if swapping these tiles solved the puzzle
					JOptionPane.showMessageDialog(this, "Congrats! You solved the puzzle.");
				}
			}
			// if a tileButton NOT adjacent to empty was clicked, show a helpful warning
			else {
				JOptionPane.showMessageDialog(this, "Make sure youre selecting a tile adjacent to the empty tile.");
			}

		}
	}

}
