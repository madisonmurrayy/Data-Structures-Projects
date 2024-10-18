package assign06;

import java.util.NoSuchElementException;

/**
 * This class creates a generic stack to be used in the web browser class
 * 
 * @param <E>
 * 
 * @author Madison Murray and Zoe Linn
 * @version Fed 29, 2024
 */
public class LinkedListStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list;

	/**
	 * Constructor
	 */
	public LinkedListStack() {
		list = new SinglyLinkedList<E>();
	}

	/**
	 * Removes all of the elements from the stack.
	 */
	@Override
	public void clear() {
		list.clear();
	}

	/**
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack cannot be empty");
		}
		return (E) list.get(0);
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public E pop() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack cannot be empty");
		}
		E head = (E) list.get(0);
		list.deleteFirst();
		return head;
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	@Override
	public void push(E element) {
		list.insertFirst(element);
	}

	/**
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

}
