package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * This class contains methods to sort a generic ArrayList by mergesort and
 * quicksort
 * 
 * @author Zoe Linn and Madison Murray
 * @version Feb 18, 2024
 */
public class ArrayListSorter {
	private Comparator<Integer> comp;
	private static int threshold = 1;

	public static void setThreshold(int newThreshold) {
		threshold = newThreshold;
	}

	/**
	 * Driver method which sorts a generic array with mergesort
	 * 
	 * @param <T> data type
	 * @param arr to be sorted
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {

		mergesort(arr, 0, arr.size() - 1, new ArrayList<T>(arr));
	}

	/**
	 * Recursive method to call mergesort and merge on portions of an array
	 * 
	 * @param <T>      data type
	 * @param arr      to be sorted
	 * @param low      array portion index
	 * @param high     array portion index
	 * @param extraArr to store sorted portion
	 */
	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, int low, int high,
			ArrayList<T> extraArr) {

		// check for insertion sort
		if (high - low < threshold) {
			ArrayListSorter.insertionsort(arr, low, high);

			return;
		}

		int mid = low + (high - low) / 2;
		mergesort(arr, low, mid, extraArr);
		mergesort(arr, mid + 1, high, extraArr);

		// combine
		merge(arr, low, mid + 1, high, extraArr);
	}

	/**
	 * Private helper method which merges the smaller arrays passed through the
	 * driver's call
	 * 
	 * @param <T>      data type
	 * @param leftArr
	 * @param rightArr
	 * @param arr      to be merged
	 * @param extraArr to store sorted portion
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, int lowIndex, int midIndex,
			int highIndex, ArrayList<T> extraArr) {

		int leftSize = midIndex - lowIndex;// size of first array
		int rightSize = highIndex - midIndex + 1; // size of second array
		int i = 0;// left pointer
		int j = 0; // right pointer

		while (i < leftSize && j < rightSize) {
			// left pointer less than pivot value
			if (arr.get(lowIndex + i).compareTo(arr.get(midIndex + j)) < 0) {
				extraArr.set(i + j, arr.get(lowIndex + i));
				i++;
			}

			// right pointer less than pivot value
			else {
				extraArr.set(i + j, arr.get(midIndex + j));
				j++;
			}
		}

		// merge leftover elements
		while (i < leftSize) {
			extraArr.set(i + j, arr.get(lowIndex + i));
			i++;
		}
		while (j < rightSize) {
			extraArr.set(i + j, arr.get(midIndex + j));
			j++;
		}

		// copy over merged array
		for (int k = 0; k < leftSize + rightSize; k++) {
			arr.set(lowIndex + k, extraArr.get(k));
		}
	}

	/**
	 * Insertion sort method to be used in mergesort when threshold is met or in
	 * quicksort when size <4
	 * 
	 * @param <T>   data type
	 * @param arr   to be sorted
	 * @param start
	 * @param end
	 */
	private static <T extends Comparable<? super T>> void insertionsort(ArrayList<T> arr, int start, int end) {

		for (int i = start + 1; i < end + 1; i++) {
			for (int j = i - 1; j >= start && (arr.get(j).compareTo(arr.get(j + 1)) > 0); j--) {
				T temp = arr.get(j);
				arr.set(j, arr.get(j + 1));
				arr.set(j + 1, temp);
			}
		}
	}

	/**
	 * Driver method for quicksort
	 * 
	 * @param <T> object type of elements int the array to be sorted
	 * @param arr ArrayList to be sorted
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {

		quicksort(arr, 0, arr.size() - 1);

	}

	/**
	 * This method performs a reursive quicksort on the generic ArrayList passed
	 * through
	 * 
	 * @param <T>  data type
	 * @param arr  to be sorted
	 * @param low  bounds of arr
	 * @param high bounds of arr
	 */
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int low, int high) {

		if (low >= high) {
			return;
		} else if (high - low < 4) {
			ArrayListSorter.insertionsort(arr, low, high);
			return;

		}
		// choose pivot and partition
		int pivotIndex = choosePivot(arr, low, high);
		int partitionIndex = partition(arr, low, pivotIndex, high);

		quicksort(arr, low, partitionIndex - 1);
		quicksort(arr, partitionIndex + 1, high);

	}

	/**
	 * This method partitions and correctly positions the pivot value
	 * 
	 * @param <T>        data type
	 * @param arr        to be partitioned
	 * @param low        bounds
	 * @param pivotIndex of pivot origionally
	 * @param high       bounds
	 * @return the correct index for pivot
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int low, int pivotIndex,
			int high) {

		// put pivot at last index
		T temp = arr.get(high);
		arr.set(high, arr.get(pivotIndex));
		arr.set(pivotIndex, temp);

		int i = low;
		int j = high - 1;

		while (true) {
			while ((arr.get(i).compareTo(arr.get(high))) < 0 && i < high) {
				i++;
			}

			while ((arr.get(j).compareTo(arr.get(high))) > 0 && j > low) {
				j--;
			}

			if (i >= j) {
				break;
			}

			temp = arr.get(i);
			arr.set(i, arr.get(j));
			arr.set(j, temp);

			i++;
			j--;
		}

		temp = arr.get(i);
		arr.set(i, arr.get(high));
		arr.set(high, temp);

		return i;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * ascending order
	 * 
	 * @param size
	 * @return generated array
	 */
	public static ArrayList<Integer> generateAscending(int size) {

		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			arr.add(i);
		}
		return arr;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * random order
	 * 
	 * @param size
	 * @return generated array
	 */
	public static ArrayList<Integer> generatePermuted(int size) {

		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			arr.add(i);
		}
		Collections.shuffle(arr, new Random(9));
		return arr;
	}

	/**
	 * This method generates and returns an ArrayList of integers 1 to size in
	 * descending order
	 * 
	 * @param size
	 * @return generated array
	 */
	public static ArrayList<Integer> generateDescending(int size) {

		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = size; i >= 1; i--) {
			arr.add(i);
		}
		return arr;
	}

	/**
	 * Private helper method to choose the pivot value
	 * 
	 * @param <T>  data type
	 * @param arr  to be chosen from
	 * @param low
	 * @param high
	 * @return index of pivot
	 */
	private static <T extends Comparable<? super T>> int choosePivot(ArrayList<T> arr, int low, int high) {

		// change this line accordingly for tests
		int pivot = pivot2(arr, low, high);

		return pivot;
	}

	/**
	 * private helper method to select a random pivot
	 * 
	 * @param <T>  data type
	 * @param arr  to be chosen from
	 * @param low
	 * @param high
	 * @return index of pivot
	 */
	private static <T extends Comparable<? super T>> int pivot1(ArrayList<T> arr, int low, int high) {

		// pick a random index between low and high
		int random = (int) ((Math.random() * (high - low)) + low);

		return random;

	}

	/**
	 * private helper method to select pivot at middle index
	 * 
	 * @param <T>  data type
	 * @param arr  to be chosen from
	 * @param low
	 * @param high
	 * @return index of pivot
	 */
	public static <T extends Comparable<? super T>> int pivot2(ArrayList<T> arr, int low, int high) {

		return low + ((high - low) / 2);

	}

	/**
	 * private helper method to select pivot at the median index between low , mid
	 * and high
	 * 
	 * @param <T>  data type
	 * @param arr  to be chosen from
	 * @param low
	 * @param high
	 * @return index of pivot
	 */
	private static <T extends Comparable<? super T>> int pivot3(ArrayList<T> arr, int low, int high) {

		// find comparisons between each variable above

		int mid = low + ((high - low) / 2);

		if (arr.get(low).compareTo(arr.get(mid)) < 0) {
			if (arr.get(mid).compareTo(arr.get(high)) < 0) {
				return mid;
			}

			else {
				if (arr.get(low).compareTo(arr.get(high)) < 0) {
					return high;
				} else {
					return low;
				}

			}
		} else {
			if (arr.get(low).compareTo(arr.get(high)) < 0) {
				return low;
			} else {
				if (arr.get(mid).compareTo(arr.get(high)) < 0) {
					return high;
				} else {
					return mid;
				}
			}

		}
	}

}
