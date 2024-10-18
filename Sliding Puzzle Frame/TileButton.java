package assign09;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This class creates TileButton objects, extending JButton and adding a row,
 * column and imageID to attach to every object along with an icon
 * 
 * @author Madison Murray
 * @version Nov 12 2023
 */
public class TileButton extends JButton {

	private static final long serialVersionUID = 1L;
	private final int ROW; // holds solved row position
	private final int COLUMN; // holds solved column position
	private int id; // holds current ID location

	/**
	 * Creates a new tileButton
	 * 
	 * @param filename attatches to imageIcon
	 * @param row      for solved position
	 * @param column   for solved position
	 * @param imageID  for current position
	 */
	public TileButton(String filename, int row, int column, int imageID) {
		super(new ImageIcon(filename)); // call JButton constructor with current icon
		ROW = row;
		COLUMN = column;
		id = imageID;
	}

	/**
	 * Gets the solved row position
	 * 
	 * @return int of solved row position
	 */
	public int getRow() {
		
		return this.ROW;
	}

	/**
	 * Gets the solved column position
	 * 
	 * @return int of the solved column position
	 */
	public int getColumn() {
		
		return this.COLUMN;
	}

	/**
	 * Gets the current ID placement of tile
	 * 
	 * @return int of current imageID
	 */
	public int getImageID() {
		
		return this.id;
	}

	/**
	 * Swaps this TileButton with other TileButton
	 * 
	 * @param TileButton object to be swapped
	 */
	public void swap(TileButton other) {

		// hold information from other TileButton
		int tempID = other.getImageID();
		Icon tempIcon = other.getIcon();

		// overwrite old id and Icon in other TileButton with info from this TileButton
		other.id = this.getImageID();
		other.setIcon(this.getIcon());

		// set this id and icon to the values held in temp
		id = tempID;
		this.setIcon(tempIcon);
	}

}
