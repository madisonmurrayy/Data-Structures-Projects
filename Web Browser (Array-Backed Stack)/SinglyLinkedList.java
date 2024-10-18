package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This general linked list class is implemented within a stack, holding extra
 * data to be attached and modified in helping the stack's behavior. This class
 * contains methods to insert, delete, get and clear objects from a linked list
 *
 * @author Madison Murray and Zoe Linn
 * @version Feb 27, 2024
 * @param <E> object type to be used in list
 */
public class SinglyLinkedList<E> implements List<E> {

	private Node head;
	private int elementCount;
	private Iterator<E> iterator;

	/**
	 * Constructor that creates a new SinglyLinkedList object with no elements of
	 * size 0
	 */
	public SinglyLinkedList() {
		head = null;
		iterator = new SinglyLinkedListIterator();
		elementCount = 0;
	}

	/**
	 * Inserts an element at the beginning of the list. O(1) for a singly-linked
	 * list.
	 * 
	 * @param element - the element to add
	 */
	@Override
	public void insertFirst(E element) {
		head = new Node(element, head);
		elementCount++;
	}

	/**
	 * Inserts an element at a specific position in the list. O(N) for a
	 * singly-linked list.
	 * 
	 * @param index   - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index > size())
	 */
	@Override
	public void insert(int index, E element) throws IndexOutOfBoundsException {
		iterator = iterator();
		Node current = null;
		if (index < 0 || index > elementCount) {
			throw new IndexOutOfBoundsException("Index cannot be out of bounds");
		}
		if (index == 0) {
			insertFirst(element);
		} else {
			for (int i = 0; i < index; i++) {
				current = getNode(i);
				iterator.next();
				// pointer will be one more than i, index will be i
				// loop lands on the last element before insertion
			}
			// relink the current item to the new item
			Node temp = current.next;
			current.next = new Node(element, current.next);
			elementCount++;

		}

		// TODO
	}

	/**
	 * Gets the first element in the list. O(1) for a singly-linked list.
	 * 
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (elementCount == 0) {
			throw new NoSuchElementException("List cannot be empty");
		}
		return head.data;
	}

	/**
	 * Gets the element at a specific position in the list. O(N) for a singly-linked
	 * list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		iterator = iterator();
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		/*
		 * Node temp = head; for(int i = 0; i< index; i++) { temp = head.next; } return
		 * temp.data;
		 */
		E temp = null;

		if (index == 0) {
			temp = head.data;

		}

		for (int i = 0; i <= index; i++) {
			temp = iterator.next();
		}
		return temp;
	}

	/**
	 * Deletes and returns the first element from the list. O(1) for a singly-linked
	 * list.
	 * 
	 * @return the first element
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E deleteFirst() throws NoSuchElementException {
		if (elementCount == 0) {
			throw new NoSuchElementException("List cannot be empty");
		}
		if (elementCount == 1) {
			elementCount--;
			return head.data;
		}
		Node temp = head;
		head = head.next;
		elementCount--;
		return temp.data;
	}

	/**
	 * Deletes and returns the element at a specific position in the list. O(N) for
	 * a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 *                                   index >= size())
	 */
	@Override
	public E delete(int index) throws IndexOutOfBoundsException {
		iterator = iterator();
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if (index == 0) {
			return deleteFirst();
		}
		E temp = null;
		/*
		 * if(index == 0) { iterator.next(); temp = head.next.data; head = head.next;
		 * elementCount--; return temp;
		 * 
		 * }
		 */

		for (int i = 0; i <= index; i++) {
			// pointing to the index to remove
			temp = iterator.next();
		}

		iterator.remove();
		return temp;

	}

	/**
	 * Determines the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * singly-linked list.
	 * 
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	@Override
	public int indexOf(E element) {
		iterator = iterator();
		E comp = null;
		
		int i = 0;
		while (i < size()) {
			comp = iterator.next();
			if (comp.equals(element)) {
				return i;
			}
			i++;
		}
		return -1;

	}

	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return elementCount;
	}

	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return true if this collection contains no elements; false, otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (elementCount == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes all of the elements from this list. O(1) for a singly-linked list.
	 */
	@Override
	public void clear() {
		head = null;
		elementCount = 0;

	}

	/**
	 * Generates an array containing all of the elements in this list in proper
	 * sequence (from first element to last element). O(N) for a singly-linked list.
	 * 
	 * @return an array containing all of the elements in this list, in order
	 */
	@Override
	public Object[] toArray() {
		iterator = iterator();
		if (size() == 0) {
			return new Object[0];
		}
		Object[] arr = new Object[size()];

		for (int i = 0; i < size(); i++) {
			arr[i] = iterator.next();
		}
		return arr;
	}

	/**
	 * @return an iterator over the elements in this list in proper sequence (from
	 *         first element to last element)
	 */
	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	// Make a nested class to represent a node
	private class Node {
		public E data;
		public Node next;

		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * Helper method to retrieve the node at the given position.
	 * 
	 * NOTE: It is a precondition that pos >= 0.
	 * 
	 * @param pos 0-indexed position of the node
	 * @return node at pos
	 */
	
	private Node getNode(int pos) {

		Node temp = head;
		for (int i = 0; i < pos; i++) {
			temp = temp.next;
		}
		return temp;
	}

	/**
	 * private nested class that creates singlylinkedlist iterator objects
	 */
	public class SinglyLinkedListIterator implements Iterator<E> {

		private int pointer;
		private boolean nextCall;

		/**
		 * Constructor that creates a SinglyLinkedListIterator object with the pointer
		 * starting at 0 that cannot call next()
		 */
		public SinglyLinkedListIterator() {
			pointer = 0;
			nextCall = false;
		}

		/**
		 * checks whether or not this list has a next element
		 * 
		 * @return true if this list has a next element, false if not
		 */
		@Override
		public boolean hasNext() {
			return pointer < size();
		}

		/**
		 * Returns the item that the pointer was on, allows for a nextCall, and
		 * increments the pointer by one
		 * 
		 * @throws a NoSuchElementException if there is no element to go to next
		 * @return item - the item that the pointer was on
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();

			}

			E item = getNode(pointer).data;

			nextCall = true;

			pointer++;
			return item;
		}

		/**
		 * removes the item that was before next, decrements the pointer, decrements the
		 * elementCount, and doesn't allow a nextCall
		 * 
		 * @throws a NoSuchElementException if there is no element to go to next
		 * 
		 */
		public void remove() {
			// check for removing first element and reset head(if pointer ==1)
			if (!nextCall) {
				// SHOULD THIS BE NO SUCH ELEMENT
				throw new IllegalStateException("next needs to be called");

			}
			/*
			 * //DONT NEED THIS for(int i = pointer; i < size(); i++) { Node temp =
			 * getNode(i); insert(i-1, temp.data);
			 * 
			 * }
			 */

			if (pointer == 1) {
				head = head.next;
				nextCall = false;
				elementCount--;
				return;
			}

			Node previousNode = getNode(pointer - 2);
			previousNode.next = previousNode.next.next;

			pointer--;
			elementCount--;

			nextCall = false;

			// EDIT THIS METHOD
			/*
			 * all we have to do is get the previous node(before the one pointed to) and
			 * update "previousNode"'s next reference in order to skip the current node
			 * 
			 * aka previousNode = getNode(pointer -1); previousNode.next =
			 * previousNode.next.next;
			 * 
			 * keep lines 304-406
			 */
		}

	}

}
