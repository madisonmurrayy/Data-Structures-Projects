package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This class simulates the behavior of the browsers back button and the other
 * to help stimulate behavior of the browsers forward button.
 * 
 * @author Zoe Linn and Madison Murray
 * @version Feb 27, 2024
 */
public class WebBrowser {

	private Stack<URL> forward;
	private Stack<URL> back;
	private URL current;

	/**
	 * Constructor that creates a new web browser with no previously visited
	 * webpages and no pages to visit
	 */
	public WebBrowser() {
		forward = new LinkedListStack<URL>();
		back = new LinkedListStack<URL>();
	}

	/**
	 * Constructor that creates
	 * 
	 * @param history
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		forward = new LinkedListStack<URL>();
		back = new LinkedListStack<URL>();

		current = history.getFirst();
		for (int i = history.size() - 1; i > 0; i--) {
			back.push(history.get(i));
		}
	}

	/**
	 * This method simulates visiting a webpage, given as a URL. Note that calling
	 * this method should clear the forward button stack, since there is no URL to
	 * visit next.
	 * 
	 * @param webpage
	 */
	public void visit(URL webpage) {
		if (!(current == null)) {
			back.push(current);
		}

		forward.clear();
		current = webpage;
	}

	/**
	 * This method simulates using the back button, returning the URL visited.
	 * NoSuchElementExceptionLinks to an external site. is thrown if there is no
	 * previously-visited URL.
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */
	public URL back() throws NoSuchElementException {
		forward.push(current);
		current = back.pop();
		return current;
	}

	/**
	 * This method simulates using the forward button, returning the URL visited.
	 * NoSuchElementException is thrown if there is no URL to visit next.
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */
	public URL forward() throws NoSuchElementException {
		if (forward.isEmpty()) {
			throw new NoSuchElementException("No more pages to visit");
		}
		current = forward.pop();
		return current;
	}

	/**
	 * This method generates a history of URLs visited, as a list of URL objects
	 * ordered from most recently visited to least recently visited (including the
	 * "current" webpage visited), without altering subsequent behavior of this web
	 * browser. "Forward" URLs are not included. The behavior of the method must be
	 * O(N), where N is the number of URLs.
	 * 
	 * @return
	 */
	public SinglyLinkedList<URL> history() {
		SinglyLinkedList<URL> list = new SinglyLinkedList<URL>();
		SinglyLinkedList<URL> flippedList = new SinglyLinkedList<URL>();
		Stack<URL> tempStack = new LinkedListStack<URL>();

		// while back has history in it
		while (!back.isEmpty()) {
			// push the top of the back stack onto tempStack
			tempStack.push(back.pop());
			// insert that element to the front of the list(last element in back = first
			// element in list)

			list.insertFirst(tempStack.pop());

		}

		// put the back stack back together
		for (int i = 0; i < list.size(); i++) {
			flippedList.insertFirst(list.get(i));
			back.push(list.get(i));
		}
		if (!(current == null)) {
			flippedList.insertFirst(current);
		}

		return flippedList;
	}

}
