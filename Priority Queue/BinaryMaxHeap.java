package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class creates a BinaryMaxHeap of generic type using a PriorityQueue.
 * 
 * @author Madison Murray, Zoe Linn
 * @version April 8, 2024
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	private E[] arr; // generic backing array
	private int size;
	//when size exceeds arr.length-1, grow backing array * 2 (use assign3 instructions said Eric)

	private Comparator<? super E> cmp; //when this is null, we know that we use natural ordering
	
	
	//store root at node 0 --> children at 2i + 1, 2i + 2
	
	
	/**
	 * If this constructor is used to create an empty binary heap, it is assumed
	 * that the elements are ordered using their natural ordering (i.e., E
	 * implements Comparable<? super E>)
	 * 
	 * constructs a BinaryMaxHeap using natural ordering
	 */
	public BinaryMaxHeap() {
		size = 0;
		arr = (E[]) new Object[10];
	}
	
	/**
	 * If this constructor is used to create an empty binary heap, it is assumed
	 * that the elements are ordered using the provided Comparator object.
	 * 
	 * constructs a BinaryMaxHeap using a Comparator object
	 * 
	 * @param cmp
	 */
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		size = 0;
		arr = (E[]) new Object[10];
		this.cmp = cmp;
	}
	
	/**
	 * If this constructor is used, then the binary heap is constructed from the
	 * given list. Also, it is assumed that the elements are ordered using their
	 * natural ordering (i.e., E implements Comparable<? super E>)
	 * 
	 * RECALL: Using the buildheap operation, we can construct a binary heap from
	 * these N items in O(N) time, which is more efficient than adding them to the
	 * binary heap one at a time. This constructor must use such an operation.
	 * 
	 * @param list
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		// ok so by adding all at once I assume he means in order of the list and then
		// reorganizing SO:
		
		size = 0;
		arr = (E[]) new Object[10];
		
		// add each item in list to arr, growing when needed
		buildHeap(list);
		
		// fufill order using buildHeap code
		// we'll probs just call the buildHeap helper method
	}

	/**
	 * If this constructor is used, then the binary heap is constructed from the
	 * given list (see RECALL note above). Also, it is assumed that the elements are
	 * ordered using the provided Comparator object
	 * 
	 * @param list
	 * @param cmp
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		// same thing as last but with comparator that we pass through buildHeap
		
		// add each item in list to arr, growing when needed
		// fufill order using buildHeap code
		// we'll probs just call the buildHeap helper method	
		
		size = 0;
		arr = (E[]) new Object[10];
		
		this.cmp = cmp;
		buildHeap(list);
	}
	
	/**
	 * suggested private helper method NEEDS PARAMS
	 * 
	 * O(N) -> N items * O(1) insertion
	 */
	private void buildHeap(List<? extends E> list) {
		// add everything to the tree/arr --> structure 
		for(int i = 0; i < list.size(); i++) {
			if(arr.length - 1 < i) {
				growArr();
			}
			arr[i] = list.get(i);
			size++;
		}
		
		//fufill order:
		for(int i = ((size() - 1)/2); i >= 0; i--) {
//			System.out.println(i + " i");
			percolateDown(i);
		}
	}
	
	/**
	 * suggested private helper method NEEDS PARAMS
	 * 
	 * private helper method that iterates through the tree by moving up and fufills order
	 */
	private void percolateUp(int index) {
		int count=0;
		//System.out.println("percolating " + arr[index] + " up.");
		if(index == 0) {
			return;
		}
		
		if(innerCompare(getParent(index), index) < 0) {
			//swap
			E temp = arr[index];
			arr[index] = arr[getParent(index)];
			arr[getParent(index)] = temp;
			count++;
			//System.out.println("swapping " + temp + " and " + arr[index]);
			percolateUp(getParent(index));
		} 	
		//System.out.println("we made " + count + " swaps");
		return;
		
		//check if root
			//check if parent is smaller
			//	-yes
			//	 swap with parent
			//	 continue
		
			//	-no
			//	 if parent is bigger, we're done
	}
	
	/**
	 * suggested private helper method NEEDS PARAMS
	 * 
	 * private helper method that iterates through the tree by moving down and fufills order
	 */
	private void percolateDown(int index) {
		
//		System.out.println("percolating " + arr[index] + " down.");

		// check if leaf
		if((getLeftChild(index) == 0) && (getRightChild(index) == 0)) {
			return;
		} else if ((arr[getLeftChild(index)] == null) && (arr[getRightChild(index)] == null)) {
			return;
		} else if ((arr[getLeftChild(index)] == null) && (getRightChild(index) == 0)) {
			return;
		}
		try {
		if(innerCompare(getRightChild(index), index) > 0) {
			//swap
			E temp = arr[index];
			arr[index] = arr[getRightChild(index)];
			arr[getRightChild(index)] = temp;
//			System.out.println("swapping " + temp + " and " + arr[index]);

			percolateDown(getRightChild(index));
		} 	
		
		if(innerCompare(getLeftChild(index), index) > 0) {
			//swap
			E temp = arr[index];
			arr[index] = arr[getLeftChild(index)];
			arr[getLeftChild(index)] = temp;
//			System.out.println("swapping " + temp + " and " + arr[index]);

			percolateDown(getLeftChild(index));
		}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
		
		return;

	}
	
	/**
	 * innerCompare method is intended to isolate your decision of whether to invoke
	 * a Comparable or Comparator method to just one place in your program. Also,
	 * you should refer again to the "Tips for handling generics" section of
	 * Assignment 3
	 * 
	 * suggested private helper method
	 * 
	 * @return true if we're using Comparator, false if we're using natural ordering
	 */
	private int innerCompare(int index1, int index2) {

		
		if(arr[index2] == null || arr[index1] == null) {
			return 0;
		} 

		if ((cmp == null)) {
			// using comparable - compareTo
			return (((Comparable<? super E>) arr[index1]).compareTo(arr[index2]));

		}
		
		return cmp.compare(arr[index1], arr[index2]);

	}
	
	private void growArr() {
		E[] temp = (E[]) new Object[(int) Math.pow(arr.length, 2)]; // creates an empty array double the size
		for (int i = 0; i < size; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
	}
	
	/**
	 * 
	 * @param parent
	 * @return 0 if out of bounds
	 */
	private int getLeftChild(int parent) {
		int temp = parent * 2 + 1;
		if(temp > size) {
			return 0;
		}
		return temp;
	}
	
	private int getRightChild(int parent) {
		int temp = parent * 2 + 2;
		if(temp > size) {
			return 0;
		}
		return temp;
	}
	
	private int getParent(int child) {
		int temp = (child - 1)/2;
		return temp;
	}
	
	//create private helper that extends backing array to double size
	
	@Override
	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	public void add(E item) {		
		//if arr is empty, item stored at arr[0]
		
		//add item to the next available space @arr[size] (structure)
		//PERCOLATE UP until we're satisfied. 
		//satisfy by comparing the child to the parent until child is less than the parent
		//Options?
		// --> A) keep track of parent
		// --> B) find out if we're left or right child, and find parent based off of that
		// -----> if left, parent = i/2, if right, parent = (i+1)/2 - 1
		
		if(size == arr.length) {
			growArr();
		}
		
		arr[size] = item;
		
		//System.out.println(arr[size]);
		
		percolateUp(size);
		
		size++;
	}

	@Override
	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException {
		//return the first element in arr(max heap)
		return arr[0];
	}

	@Override
	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax() throws NoSuchElementException {

		if(isEmpty()) {
			throw new NoSuchElementException("Priority Queue is empty!");
		}
		
		E temp = arr[0];
		arr[0] = arr[size - 1];
		arr[size - 1] = null;
		
		size--;
		
		percolateDown(0);
		
		return temp;
	}

	@Override
	/**
	 * Returns the number of items in this priority queue.
	 * O(1)
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	public boolean isEmpty() {
		//if size is 0, return true. return false
		if(size == 0) {
			return true;
		}
		
		return false;
	}

	@Override
	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	public void clear() {
		size = 0;
		arr= (E[]) new Object[10]; 

	}

	@Override
	/** 
	 * Creates and returns an array of the items in this priority queue,
	 * in the same order they appear in the backing array.
	 * O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item 
	 * must be stored at index 0 in the returned array, regardless of 
	 * whether it is in stored there in the backing array.) 
	 */
	public Object[] toArray() {
				
		E[] toReturn = (E[]) new Object[size];
		
		for(int i = 0; i < size; i++) {
			
			toReturn[i] = arr[i];
			
			 
		}
		
			
		return toReturn;
	}

}
