package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in a list.
 * 
 * @author Erin Parker, Madison Murray, Zoe Linn
 * @version April 8, 2024
 */
public class FindKLargest {
	
	/**
	 * Determines the k largest items in the given list, using a binary max heap and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		List<E> backwards =  new ArrayList<E>(k);
		List<E> toReturn =  new ArrayList<E>(k);

		//E[] arr = (E[]) new Object[10];
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items);
		
		for(int i =1; i<=k; i++) {
//			System.out.println(i);
			E toadd = heap.extractMax();
//			System.out.println("to add to array: " + toadd);
			backwards.add(toadd);
		}
		
		for(int i=0; i<k;i++) {
			toReturn.add(backwards.get(i));
		}
		return toReturn;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		List<E> backwards =  new ArrayList<E>(k);
		List<E> toReturn =  new ArrayList<E>(k);

		//E[] arr = (E[]) new Object[10];
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items, cmp);
		
		for(int i =1; i<=k; i++) {
//			System.out.println(i);
			E toadd = heap.extractMax();
//			System.out.println("to add to array: " + toadd);
			backwards.add(toadd);
		}
		
		for(int i=0; i<k;i++) {
			toReturn.add(backwards.get(i));
		}
		return toReturn;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine and the 
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
		List<E> toReturn =  new ArrayList<E>(k);

		Collections.sort(items);
		
		List<E> backwards =  new ArrayList<E>(k);

		//E[] arr = (E[]) new Object[10];		
		for(int i =0; i<k; i++) {
			
			backwards.add(items.get(i));
		}
		
		for(int i=0; i<k;i++) {
			toReturn.add(backwards.get(i));
		}
		return toReturn;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k - the number of largest items
	 * @param cmp - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		List<E> toReturn =  new ArrayList<E>(k);

		Collections.sort(items, cmp);
		
		List<E> backwards =  new ArrayList<E>(k);

		//E[] arr = (E[]) new Object[10];		
		for(int i =0; i<k; i++) {
			
			backwards.add(items.get(i));
		}
		
		for(int i=0; i<k;i++) {
			toReturn.add(backwards.get(i));
		}
		return toReturn;
	}
}