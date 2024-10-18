package assign04;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class which handles integer arrays and sorts them according to method called
 * naturally and in order to form the biggest number possible
 * 
 * @author Madison Murray and Zoe Linn
 * @version Feb 2 2024
 */
public class LargestNumberSolver {

	private Comparator<Integer> comp;

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 * 
	 * @param <T> Object type to be held in the basic array
	 * @param arr to be sorted
	 * @param cmp Comparator object to be sorted as defined in method body
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {

		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && (cmp.compare(arr[j], arr[j + 1]) > 0); j--) {
				T temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
			}
		}
	}

	/**
	 * This method returns the largest number that can be formed by arranging the
	 * integers of the given array, in any order. If the array is empty, the largest
	 * number that can be formed is 0. This method must not alter the given array
	 * and must call your insertionSort method with a Comparator or lambda
	 * expression that you design.
	 * 
	 * @param array to be sorted by insertion sort
	 * @return a BigInteger of the value in the
	 */
	public static BigInteger findLargestNumber(Integer[] arr) {

		Integer[] integers = copyArray(arr);
		insertionSort(integers, new InsertionSortComparator<Integer>());
		StringBuilder largeNumber = new StringBuilder(integers[0].toString());
		for (int i = 1; i < integers.length; i++) {
			largeNumber.append(integers[i].toString());
		}
		return new BigInteger(largeNumber.toString());
	}

	/**
	 * Creates a copy of a given array at a different place in memory
	 * 
	 * @param arr to be copied
	 * @return the object type to be returned from array
	 */
	private static Integer[] copyArray(Integer[] arr) {

		Integer[] newArray = new Integer[arr.length];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = arr[i];
		}
		return newArray;
	}

	/**
	 * This method returns the largest int value that can be formed by arranging the
	 * integers of the given array, in any order. An OutOfRangeException Download
	 * OutOfRangeExceptionis thrown if the largest number that can be formed is too
	 * large for the int data type. Logic for solving the problem of determining the
	 * largest number should not appear again in this method — call an existing
	 * public method or a helper method.
	 * 
	 * @param arr to be copied
	 * @return int value of the largest into found array
	 * @throws OutOfRangeException
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException {

		Integer[] integers = copyArray(arr);
		try {
			Integer.parseInt(findLargestNumber(integers).toString());

		} catch (NumberFormatException e) {
			throw new OutOfRangeException("Number too big to be an int");
		}

		return Integer.parseInt(findLargestNumber(integers).toString());

	}

	/**
	 * This method behaves the same as the previous method, but for data type long
	 * instead of data type int.
	 * 
	 * @param arr to be copied
	 * @return long value of the largest combined
	 * @throws OutOfRangeException
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException {

		Integer[] integers = copyArray(arr);
		try {
			Long.parseLong(findLargestNumber(integers).toString());

		} catch (NumberFormatException e) {
			throw new OutOfRangeException("Number too big to be an int");
		}

		return Long.parseLong(findLargestNumber(integers).toString());
	}

	/**
	 * This method sums the largest numbers that can be formed by each array in the
	 * given list. This method must not alter the given list.
	 * 
	 * @param list to be totaled
	 * @return BigInteger value of all numbers formed by each array
	 */
	public static BigInteger sum(List<Integer[]> list) {

		BigInteger bigNum = new BigInteger("0");
		for (int i = 0; i < list.size(); i++) {
			bigNum = bigNum.add(findLargestNumber(list.get(i)));
		}
		return bigNum;
	}

	/**
	 * This method determines the kth largest number that can be formed by each
	 * array in the given list. E.g., if k=0 returns the largest overall, if
	 * k=list.size()-1 returns the smallest overall. This method returns the
	 * original array that represents the kth largest number, not the kth largest
	 * number itself. An IllegalArgumentExceptionLinks to an external site. is
	 * thrown if k is not a valid position in the list
	 * 
	 * @param list    to be searched
	 * @param k'value of kth largest number formed by arrays
	 * @return Integer value of largest number found
	 * @throws IllegalArgumentException
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {

		if (k >= list.size()) {
			throw new IllegalArgumentException("k is outside of list size");
		}

		BigInteger[] bigIntList = new BigInteger[list.size()];

		for (int i = 0; i < list.size(); i++) {
			bigIntList[i] = findLargestNumber(list.get(i));
		}

		//insertionSort(bigIntList, (o1, o2) -> (o2).compareTo(o1));
		Arrays.sort(bigIntList, (o1, o2) -> (o2).compareTo(o1));
		BigInteger target = bigIntList[k];

		for (int i = 0; i < list.size(); i++) {
			if (findLargestNumber(list.get(i)).equals(target)) {
				return list.get(i);
			}
		}

		return new Integer[] { 0 };
	}

	/**
	 * This method generates list of integer arrays from an input file, such that
	 * each line corresponds to one array of integers separated by blank spaces, and
	 * returns an empty list if the file does not exist.
	 * 
	 * @param filename
	 * @return a list of Integer arrays found in the file
	 */
	public static List<Integer[]> readFile(String filename) {

		String[] numbers;
		Integer[] finalArr = new Integer[0];
		ArrayList<Integer> integers = new ArrayList<>();
		ArrayList<Integer[]> allArrays = new ArrayList<>();
		File file = new File(filename);

		try {
			Scanner input = new Scanner(file);
			String currentLine;

			while (input.hasNext()) {

				int index = 0;

				// get input from current line
				currentLine = input.nextLine();
				numbers = currentLine.split(" ");
				finalArr = new Integer[numbers.length];
				for (int i = 0; i < numbers.length; i++) {
					finalArr[i] = Integer.parseInt(numbers[i]);
				}
				allArrays.add(finalArr);
			}
			return allArrays;

		} catch (Exception e) {
			return allArrays;
		}

	}

	/**
	 * This is a private helper class which creates a Comparator Object to be use in
	 * the insertion sort
	 * 
	 * @param <T> object type to be compared
	 */
	protected static class InsertionSortComparator<Integer> implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			String sum1 = o1.toString() + o2.toString();
			String sum2 = o2.toString() + o1.toString();

			return sum2.compareTo(sum1);
		}

	}

}
