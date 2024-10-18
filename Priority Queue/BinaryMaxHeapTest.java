package assign10;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest {

	//natural and empty
	BinaryMaxHeap<Integer> integers;
	
	
	BinaryMaxHeap<String> backwardsStrings;
	ArrayList<String> strings;
	
	//natural with list
	BinaryMaxHeap<Integer> integersList;
	
	@BeforeEach
	void setUp() throws Exception {
		integers = new BinaryMaxHeap<Integer>();
		
		integers.add(14);
		integers.add(20);
		integers.add(7);
		integers.add(31);
		integers.add(22);
		integers.add(13);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(20);
		list.add(7);
		list.add(31);
		list.add(22);
		list.add(13);
		
		
		integersList = new BinaryMaxHeap<Integer>(list);
		
		strings = new ArrayList<String>();
		strings.add("e");
		strings.add("f");
		strings.add("z");
		strings.add("a");
		strings.add("c");
		strings.add("p");
		//correct order: a,c,e,f,p,z
		
		
		//implements a comparator object which will sort the strings backwards(ordered lexagraphicaly, the lowest value will be at the top)
		backwardsStrings = new BinaryMaxHeap<String>(strings, (str1, str2) -> str2.compareTo(str1));

		

		
	}

	@Test
	void testFindKHeapComparator() {
		List<String> expected = new ArrayList<>();
		expected.add("a");
		expected.add("c");
		expected.add("e");
		assertEquals(FindKLargest.findKLargestHeap(strings, 3, (str1, str2) -> str2.compareTo(str1)).get(0), expected.get(0));
	}
	
	@Test
	void testFindKHeapnatural() {
		List<String> expected = new ArrayList<>();
		expected.add("z");
		expected.add("p");
		expected.add("f");
		assertEquals(FindKLargest.findKLargestHeap(strings, 3).get(0), expected.get(0));
	}
	
	@Test
	void testFindKJavanatural() {
		List<String> expected = new ArrayList<>();
		expected.add("z");
		expected.add("p");
		expected.add("f");
		assertEquals(FindKLargest.findKLargestHeap(strings, 3).get(0), expected.get(0));
	}
	
	@Test
	void testFindKJavaComparator() {
		List<String> expected = new ArrayList<>();
		expected.add("a");
		expected.add("c");
		expected.add("e");
		assertEquals(FindKLargest.findKLargestHeap(strings, 3, (str1, str2) -> str2.compareTo(str1)).get(0), expected.get(0));
	}
//	@Test
//	void testAddAll() {
//		String[] expected = {"a","c","p","e","f","z"};
//		assertArrayEquals(expected, backwardsStrings.toArray());
//	}
//	
//	@Test
//	void testGrowArray() {
//		integers.add(100);
//		integers.add(5);
//		integers.add(45);
//		integers.add(0);
//		integers.add(21);
//		Integer[] expected = {100,45,31, 22, 21, 7, 13, 5, 14,0, 20};
//		assertArrayEquals(expected, integers.toArray());
//		assertEquals(integers.size(), 11);
//
//	}
////	@Test
////	void testAdd() {
////		Integer[] expected = {31, 22, 13, 14, 20, 7};
////		assertEquals(expected, integers.toArray());
////	}
//	
////	@Test
////	void testAddAll() {
////		Integer[] expected = {31, 22, 13, 14, 20, 7};
////		assertEquals(expected, integersList.toArray());
////	}
//	
//	@Test
//	void testExtractMax() {
//		assertEquals(31, integers.extractMax());
//		assertEquals(22, integers.extractMax());
//		assertEquals(20, integers.extractMax());
//		assertEquals(14, integers.extractMax());
//		assertEquals(13, integers.extractMax());
//		assertEquals(7, integers.extractMax());
//		assertThrows(NoSuchElementException.class, () -> {integers.extractMax();});
//	}

}
