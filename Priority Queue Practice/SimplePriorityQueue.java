package assign03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A general priority queue that supports access of the maximum element only.
 * Implements PriorityQueue interface
 * 
 * @author Madison Murray and Zoe Linn
 * @version Jan 27, 2024
 * 
 * @param <E> - the type of elements contained in this priority queue
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {

	
	private ArrayList<E> array2;
	private E[] array;
	private int size;
	private Comparator<? super E> comp;

	/**
	 * Creates an object for the SimplePriorityQueue without using a Comparator
	 * object
	 */
	public SimplePriorityQueue() {
		array2 = new ArrayList<>(); 
		array = (E[]) new Object[10];
		size = 0;
	}

	/**
	 * Creates an object for the SimplePriorityQueue with a Comparator object
	 * 
	 * @param cmp comparator object to be initialized
	 */
	public SimplePriorityQueue(Comparator<? super E> cmp) {

		comp = cmp;
		array = (E[]) new Object[10];
		array2 = new ArrayList<>();
		size = 0;
	}

	@Override
	/**
	 * Retrieves, but does not remove, the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	public E findMax() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException("Array is empty");
		}
		return array2.get(array2.size());
		//return this.array[size - 1];

	}

	/**
	 * Retrieves and removes the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException("Array is empty");
		}
		size = size - 1;
		//return this.array[size];
		return array2.remove(size-1);
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@Override
	public void insert(Object item) {

		// find the index to insert at
		int index = this.binarySearch((E) item, 0, this.size - 1);

		// checks size of array
		if (size == array.length) {
			E[] temp = (E[]) new Object[array.length * 2]; // creates an empty array double the size
			for (int i = 0; i < size; i++) {
				temp[i] = array[i];
			}
			array = temp;
		}

		// shift all elements and add in item
		for (int i = size; i > index; i--) {
			array[i] = array[i - 1];
		}
		array[index] = (E) item;
		size++;
	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E item : coll) {
			insert(item);
		}

	}

	/**
	 * Indicates whether this priority queue contains the specified element.
	 * 
	 * @param item - the element to be checked for containment in this priority
	 *             queue
	 * @return true if the item is contained in this priority queue
	 */
	@Override
	public boolean contains(Object item) {
		if (size == 0) {
			return false;
		}
		if (this.array[binarySearch((E) item, 0, this.size)].equals((E) item)) {
			return true;
		}
		return false;
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size() {

		return this.size;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {

		if (this.size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be empty
	 * when this call returns.
	 */
	@Override
	public void clear() {

		array = (E[]) new Object[10];
		size = 0;

	}

	/**
	 * Finds the index of the array at which the target element belongs - according
	 * to the sort method being used
	 * 
	 * @param target E object to insert in array
	 * @param left   bounds to search
	 * @param right  bounds to search
	 * @return int index of where target belongs
	 */
	private int binarySearch(E target, int left, int right) {

		// an empty array will always return zero
		if (size == 0) {
			return 0;
		}
		int center;
		int compare;

		// run while left bound is less than right bound
		while ((left <= right)) {

			center = left + (right - left) / 2;

			// check if a comparator was initialized
			if (!(comp == null)) {
				try {
					compare = comp.compare(array[center], target);
				} catch (NullPointerException e) {
					return center;
				}

				// if no comparator was initialized use the compareTo natural ordering for E
			} else {
				try {
					compare = (((Comparable<? super E>) array[center]).compareTo(target));
				} catch (NullPointerException e) {
					return center;
				}
			}

			// greater than the current element
			if (compare > 0) {

				right = center - 1;

				// less than the current element
			} else if (compare < 0) {
				left = center + 1;

				// equals the current element
			} else {
				return center;
			}

		}
		return left;
	}

}
