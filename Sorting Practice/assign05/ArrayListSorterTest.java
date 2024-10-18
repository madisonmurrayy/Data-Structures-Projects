package assign05;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains methods to test the ArrayListSorter class
 * 
 * @author Madison Murray and Zoe Linn
 * @version Feb 18, 2024
 */
class ArrayListSorterTest {

	ArrayList<Integer> ints = new ArrayList<>();
	ArrayList<String> strings = new ArrayList<>();
	ArrayList<Integer> biggerInts = new ArrayList<>();
	ArrayList<String> smallStrings = new ArrayList<>();
	ArrayList<Integer> generatedArray;
	ArrayList<Integer> same = new ArrayList<>();

	@BeforeEach
	void setup() {
		ints.add(34);
		ints.add(17);
		ints.add(20);
		ints.add(5);
		ints.add(0);

		biggerInts.add(34);
		biggerInts.add(144);
		biggerInts.add(14);
		biggerInts.add(124);
		biggerInts.add(54);
		biggerInts.add(64);
		biggerInts.add(4);
		biggerInts.add(74);
		biggerInts.add(134);
		biggerInts.add(24);
		biggerInts.add(44);
		biggerInts.add(154);
		biggerInts.add(164);
		biggerInts.add(0);

		strings.add("h");
		strings.add("f");
		strings.add("g");
		strings.add("z");
		strings.add("a");

		smallStrings.add("c");
		smallStrings.add("a");
		
		same.add(5);
		same.add(5);
		same.add(5);
		same.add(5);
		same.add(5);
		same.add(5);

		generatedArray = ArrayListSorter.generateAscending(40);
	}

	@Test
	void testMergeSortAlreadySorted() {
		ArrayList<Integer> expected = generatedArray;
		ArrayListSorter.mergesort(generatedArray);
		assertEquals(expected, generatedArray);
	}

	@Test
	void testQuickSortAlreadySorted() {
		ArrayList<Integer> expected = generatedArray;
		ArrayListSorter.quicksort(generatedArray);
		assertEquals(expected, generatedArray);
	}

	@Test
	void testMergeSortDescending() {
		ArrayList<Integer> descending = ArrayListSorter.generateDescending(40);
		ArrayListSorter.mergesort(descending);
		assertTrue(descending.get(0) < descending.get(descending.size() - 1));
	}

	@Test
	void testQuickSortDescending() {
		ArrayList<Integer> descending = ArrayListSorter.generateDescending(40);
		ArrayListSorter.quicksort(descending);
		assertTrue(descending.get(0) < descending.get(descending.size() - 1));
	}

	@Test
	void testMergeSortRandomlyGenerated() {
		ArrayList<Integer> expected = generatedArray;
		Collections.shuffle(generatedArray);
		ArrayListSorter.mergesort(generatedArray);
		assertEquals(expected, generatedArray);
	}

	@Test
	void testQuickSortRandomlyGenerated() {
		ArrayList<Integer> expected = generatedArray;
		Collections.shuffle(generatedArray);
		ArrayListSorter.quicksort(generatedArray);
		assertEquals(expected, generatedArray);
	}

	@Test
	void testMergesortLargeThreshold() {
		ArrayListSorter.setThreshold(biggerInts.size() - 1);
		ArrayListSorter.mergesort(biggerInts);
		assertEquals(0, biggerInts.get(0));
		assertEquals(164, biggerInts.get(biggerInts.size() - 1));

	}

	@Test
	void testMergeSortSmallString() {
		ArrayListSorter.mergesort(smallStrings);
		assertEquals("a", smallStrings.get(0));
		assertEquals("c", smallStrings.get(1));
	}

	@Test
	void testQuicksortSmallStrings() {
		ArrayListSorter.quicksort(smallStrings);
		assertEquals("a", smallStrings.get(0));
		assertEquals("c", smallStrings.get(1));
	}

	@Test
	void testMergeSortStrings() {
		ArrayListSorter.mergesort(strings);
		assertEquals("a", strings.get(0));
		assertEquals("f", strings.get(1));
		assertEquals("g", strings.get(2));
		assertEquals("h", strings.get(3));
		assertEquals("z", strings.get(4));
	}

	@Test
	void testQuicksortStrings() {
		Collections.shuffle(strings);
		ArrayListSorter.quicksort(strings);
		assertEquals("a", strings.get(0));
		assertEquals("f", strings.get(1));
		assertEquals("g", strings.get(2));
		assertEquals("h", strings.get(3));
		assertEquals("z", strings.get(4));
	}

	@Test
	void testMergeSortSmallArray() {
		ArrayListSorter.mergesort(ints);
		assertEquals(0, ints.get(0));
		assertEquals(5, ints.get(1));
		assertEquals(17, ints.get(2));
		assertEquals(20, ints.get(3));
		assertEquals(34, ints.get(4));
	}

	@Test
	void testMergeSortLargeArray() {
		ArrayListSorter.mergesort(biggerInts);
		ArrayListSorter.setThreshold(4);
		assertEquals(0, biggerInts.get(0));
		assertEquals(164, biggerInts.get(biggerInts.size() - 1));
	}

	@Test
	void testQuickSort() {
		ArrayListSorter.quicksort(ints);
		assertEquals(0, ints.get(0));
		assertEquals(5, ints.get(1));
		assertEquals(17, ints.get(2));
		assertEquals(20, ints.get(3));
		assertEquals(34, ints.get(4));
	}

	@Test
	void testQuickSortLargeArray() {
		ArrayListSorter.quicksort(biggerInts);
		assertEquals(0, biggerInts.get(0));
		assertEquals(164, biggerInts.get(biggerInts.size() - 1));
	}

	@Test
	void testGenerateAscending() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		assertEquals(arr, ArrayListSorter.generateAscending(5));
	}

	@Test
	void testGeneratePermuted() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		assertNotEquals(arr, ArrayListSorter.generatePermuted(5));
	}

	@Test
	void testGenerateDescending() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(5);
		arr.add(4);
		arr.add(3);
		arr.add(2);
		arr.add(1);
		assertEquals(arr, ArrayListSorter.generateDescending(5));
	}
	
	@Test
	void testMergeSortEmpty() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayListSorter.mergesort(arr);
		assertEquals(arr, ArrayListSorter.generateAscending(0));
	}
	
	@Test
	void testQuickSortEmpty() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayListSorter.quicksort(arr);
		assertEquals(arr, ArrayListSorter.generateAscending(0));
	}
	
	@Test
	void testQuickSortThree() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr = ArrayListSorter.generateAscending(3);
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		arr2 = ArrayListSorter.generateDescending(3);
		ArrayListSorter.quicksort(arr2);
		assertEquals(arr, arr2);
	}
	
	@Test
	void testGenerateAscendingNegative() {
		assertEquals(ArrayListSorter.generateAscending(-1), new ArrayList<Integer>());
	}
	
	@Test
	void testGenerateDescendinggNegative() {
		assertEquals(ArrayListSorter.generateDescending(-1), new ArrayList<Integer>());
	}
	
	@Test
	void testGeneratePermutedNegative() {
		assertEquals(ArrayListSorter.generatePermuted(-1), new ArrayList<Integer>());
	}
	
	@Test
	void testMergeSortAllSame() {
		ArrayList<Integer> newSame = same;
		ArrayListSorter.mergesort(same);
		assertEquals(newSame, same);
	}
	
	@Test
	void testQuickSortAllSame() {
		ArrayList<Integer> newSame = same;
		ArrayListSorter.quicksort(same);
		assertEquals(newSame, same);
	}
	
	@Test
	void testMergeSortSameWithOneExtra(){
		ArrayList<Integer> newSame = same;
		newSame.add(3);
		same.add(3);
		Collections.shuffle(same);
		ArrayListSorter.mergesort(same);
		assertEquals(newSame, same);
	}
	
	@Test
	void testQuickSortSameWithOneExtra(){
		ArrayList<Integer> newSame = same;
		newSame.add(3);
		same.add(3);
		Collections.shuffle(same);
		ArrayListSorter.quicksort(same);
		assertEquals(newSame, same);
	}
	
	@Test
	void testMergeSortAscending() {
		ArrayList<Integer> toBeSorted = ArrayListSorter.generateAscending(100);
		ArrayListSorter.mergesort(toBeSorted);
		assertEquals(ArrayListSorter.generateAscending(100), toBeSorted);
	}
	
	@Test
	void testQuickSortAscending() {
		ArrayList<Integer> toBeSorted = ArrayListSorter.generateAscending(100);
		ArrayListSorter.quicksort(toBeSorted);
		assertEquals(ArrayListSorter.generateAscending(100), toBeSorted);
	}

	
	@Test
	void testMergeSortPermuted() {
		ArrayList<Integer> toBeSorted = ArrayListSorter.generatePermuted(100);
		ArrayListSorter.mergesort(toBeSorted);
		assertEquals(ArrayListSorter.generateAscending(100), toBeSorted);
	}
	
	@Test
	void testQuickSortPermuted() {
		ArrayList<Integer> toBeSorted = ArrayListSorter.generatePermuted(100);
		ArrayListSorter.quicksort(toBeSorted);
		assertEquals(ArrayListSorter.generateAscending(100), toBeSorted);
	}

}
