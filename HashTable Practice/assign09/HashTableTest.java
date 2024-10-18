package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the HashTable class
 * 
 * @author Madison Murray, Zoe Linn
 * @version April 5, 2024
 */
class HashTableTest {

	HashTable<StudentGoodHash, Double> table = new HashTable<StudentGoodHash, Double>();
	StudentGoodHash alan;
	StudentGoodHash ada;
	StudentGoodHash edsger;
	StudentGoodHash grace;

	HashTable<Integer, Double> table2 = new HashTable<Integer, Double>();
	// 9
	Integer twenty;
	// 10
	Integer twentyone;
	// 9, should go to 6
	Integer thirtyone;
	// 1
	Integer twelve;
	// should go to 6
	Integer seventeen;

	HashTable<StudentBadHash, Double> table3 = new HashTable<StudentBadHash, Double>();
	StudentBadHash shakira;
	StudentBadHash mike;
	StudentBadHash millie;
	StudentBadHash avery;

	@BeforeEach
	void setUp() throws Exception {
		alan = new StudentGoodHash(0, "Alan", "Turing");
		ada = new StudentGoodHash(1, "Ada", "Lovelace");
		edsger = new StudentGoodHash(2, "Edsger", "Dijkstra");
		grace = new StudentGoodHash(3, "Grace", "Hopper");

		table.put(alan, 3.2);
		table.put(ada, 3.5);
		table.put(edsger, 3.8);
		table.put(grace, 4.0);

		twenty = 20;
		twentyone = 21;
		thirtyone = 31;
		twelve = 12;
		seventeen = 17;

		table2.put(twelve, 12.0);
		table2.put(twenty, 20.0);
		table2.put(twentyone, 21.0);
		table2.put(thirtyone, 31.0);
		table2.put(seventeen, 17.0);

		shakira = new StudentBadHash(0, "Shakira", "Turing");
		mike = new StudentBadHash(1, "Mike", "Lovelace");
		millie = new StudentBadHash(2, "Millie", "Dijkstra");
		avery = new StudentBadHash(3, "Avery", "Hopper");

		table3.put(shakira, 1.2);
		table3.put(mike, 1.3);
		table3.put(millie, 1.4);
		table3.put(avery, 1.5);

	}

// STUDENTGOODHASH TESTS----------->	

	@Test
	void testSize() {
		assertEquals(4, table.size());
	}

	@Test
	void testClear() {
		table.clear();
		assertEquals(0, table.size());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			table.get(alan);
		});
	}

	@Test
	void testContainsKey() {
		assertTrue(table.containsKey(alan));
		assertTrue(table.containsKey(ada));
		assertTrue(table.containsKey(edsger));
		assertTrue(table.containsKey(grace));
	}

	@Test
	void testContainsValue() {
		assertTrue(table.containsValue(3.2));
		assertTrue(table.containsValue(3.5));
		assertTrue(table.containsValue(3.8));
		assertTrue(table.containsValue(4.0));
	}

	@Test
	void testEntries() {
		ArrayList<MapEntry<StudentGoodHash, Double>> expected = new ArrayList<MapEntry<StudentGoodHash, Double>>();
		expected.add(new MapEntry<StudentGoodHash, Double>(alan, 3.2));
		expected.add(new MapEntry<StudentGoodHash, Double>(ada, 3.5));
		expected.add(new MapEntry<StudentGoodHash, Double>(edsger, 3.8));
		expected.add(new MapEntry<StudentGoodHash, Double>(grace, 4.0));

		assertTrue(table.entries().contains(expected.get(0)));
		assertTrue(table.entries().contains(expected.get(1)));
		assertTrue(table.entries().contains(expected.get(2)));
		assertTrue(table.entries().contains(expected.get(3)));
	}

	@Test
	void testGet() {
		assertEquals(table.get(alan), 3.2);
		assertEquals(table.get(ada), 3.5);
		assertEquals(table.get(edsger), 3.8);
		assertEquals(table.get(grace), 4.0);
	}

	@Test
	void testIsEmpty() {
		table.clear();
		assertTrue(table.isEmpty());
	}

	@Test
	void testFindIndexAndPut() {
		StudentGoodHash susie = new StudentGoodHash(1, "Susie", "Tusie");
		assertEquals(table.findIndex(susie), 5);
		table.put(susie, 4.2);
		assertTrue(table.containsKey(susie));
		assertEquals(table.findIndex(susie), 5);
		StudentGoodHash jessie = new StudentGoodHash(5, "Jessie", "Messy");
		assertEquals(table.findIndex(jessie), 6);

		table.put(jessie, 2.0);
		assertEquals(table.findIndex(jessie), 6);
	}

	@Test
	void testRemove() {
		table.remove(alan);
		assertFalse(table.containsKey(alan));
		table.remove(ada);
		assertFalse(table.containsKey(ada));
		table.remove(edsger);
		assertFalse(table.containsKey(edsger));
		table.remove(grace);
		assertFalse(table.containsKey(grace));
		assertEquals(table.remove(grace), null);
	}

	@Test
	void testRehash() {
		StudentGoodHash susie = new StudentGoodHash(1, "Susie", "Tusie");
		table.put(susie, 4.2);
		StudentGoodHash jessie = new StudentGoodHash(5, "Jessie", "Messy");
		table.put(jessie, 2.0);
		StudentGoodHash james = new StudentGoodHash(3, "James", "Tames");
		table.put(james, 2.6);
		assertEquals(table.size(), 7);
		assertEquals(table.findIndex(james), 4);
	}

//INTEGERS TEST -------------->

	@Test
	void testSizeInts() {
		assertEquals(5, table2.size());
	}

	@Test
	void testClearInts() {
		table2.clear();
		assertEquals(0, table2.size());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			table2.get(twenty);
		});
	}

	@Test
	void testContainsKeyInts() {
		assertTrue(table2.containsKey(twelve));
		assertTrue(table2.containsKey(twenty));
		assertTrue(table2.containsKey(twentyone));
		assertTrue(table2.containsKey(thirtyone));
		assertTrue(table2.containsKey(seventeen));

	}

	@Test
	void testContainsValueInts() {
		assertTrue(table2.containsValue(17.0));
		assertTrue(table2.containsValue(12.0));
		assertTrue(table2.containsValue(31.0));
		assertTrue(table2.containsValue(20.0));
		assertTrue(table2.containsValue(21.0));
	}

	@Test
	void testEntriesInts() {
		ArrayList<MapEntry<Integer, Double>> expected = new ArrayList<MapEntry<Integer, Double>>();
		expected.add(new MapEntry<Integer, Double>(twelve, 12.0));
		expected.add(new MapEntry<Integer, Double>(twenty, 20.0));
		expected.add(new MapEntry<Integer, Double>(twentyone, 21.0));
		expected.add(new MapEntry<Integer, Double>(thirtyone, 31.0));
		expected.add(new MapEntry<Integer, Double>(seventeen, 17.0));

		assertTrue(table2.entries().contains(expected.get(0)));
		assertTrue(table2.entries().contains(expected.get(1)));
		assertTrue(table2.entries().contains(expected.get(2)));
		assertTrue(table2.entries().contains(expected.get(3)));
		assertTrue(table2.entries().contains(expected.get(4)));

	}

	@Test
	void testGetInts() {
		assertEquals(table2.get(twenty), 20.0);
		assertEquals(table2.get(twentyone), 21.0);
		assertEquals(table2.get(seventeen), 17.0);
		assertEquals(table2.get(twelve), 12.0);
		assertEquals(table2.get(thirtyone), 31.0);

	}

	@Test
	void testIsEmptyInts() {
		table2.clear();
		assertTrue(table2.isEmpty());
	}

	@Test
	void testRemoveInts() {
		table2.remove(seventeen);
		assertFalse(table2.containsKey(seventeen));
		table2.remove(twenty);
		assertFalse(table2.containsKey(twenty));
		table2.remove(twentyone);
		assertFalse(table2.containsKey(twentyone));
		table2.remove(twelve);
		assertFalse(table2.containsKey(twelve));
		table2.remove(thirtyone);
		assertFalse(table2.containsKey(thirtyone));
		assertEquals(table2.remove(thirtyone), null);
	}

	@Test
	void testRehashInts() {
		Integer thirtyfive = 35;
		table2.put(thirtyfive, 35.0);
		assertEquals(table2.findIndex(thirtyfive), 13);
		assertEquals(table2.size(), 6);
	}

	@Test
	void testAddDuplicates() {
		Integer twelve2 = 12;
		assertEquals(table2.put(twelve2, 12.5), 12.0);
		assertEquals(table2.size(), 5);
	}

	// STUDENTBADHASH TESTS----------->

	@Test
	void testSize3() {
		assertEquals(4, table3.size());
	}

	@Test
	void testClear3() {
		table3.clear();
		assertEquals(0, table3.size());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			table3.get(shakira);
		});
	}

	@Test
	void testContainsKey3() {
		assertTrue(table3.containsKey(shakira));
		assertTrue(table3.containsKey(mike));
		assertTrue(table3.containsKey(millie));
		assertTrue(table3.containsKey(avery));
	}

	@Test
	void testContainsValue3() {
		assertTrue(table3.containsValue(1.2));
		assertTrue(table3.containsValue(1.3));
		assertTrue(table3.containsValue(1.4));
		assertTrue(table3.containsValue(1.5));
	}

	@Test
	void testEntries3() {
		ArrayList<MapEntry<StudentBadHash, Double>> expected = new ArrayList<MapEntry<StudentBadHash, Double>>();
		expected.add(new MapEntry<StudentBadHash, Double>(shakira, 1.2));
		expected.add(new MapEntry<StudentBadHash, Double>(mike, 1.3));
		expected.add(new MapEntry<StudentBadHash, Double>(millie, 1.4));
		expected.add(new MapEntry<StudentBadHash, Double>(avery, 1.5));

		assertTrue(table3.entries().contains(expected.get(0)));
		assertTrue(table3.entries().contains(expected.get(1)));
		assertTrue(table3.entries().contains(expected.get(2)));
		assertTrue(table3.entries().contains(expected.get(3)));
	}

	@Test
	void testGet3() {
		assertEquals(table3.get(shakira), 1.2);
		assertEquals(table3.get(mike), 1.3);
		assertEquals(table3.get(millie), 1.4);
		assertEquals(table3.get(avery), 1.5);
	}

	@Test
	void testIsEmpty3() {
		table3.clear();
		assertTrue(table3.isEmpty());
	}

	@Test
	void testFindIndexAndPut3() {
		StudentBadHash susie = new StudentBadHash(1, "Susie", "Tusie");
		assertEquals(table3.findIndex(susie), 9);
		table3.put(susie, 4.2);
		assertTrue(table3.containsKey(susie));
		assertEquals(table3.findIndex(susie), 9);
		StudentBadHash jessiebobessie = new StudentBadHash(5, "Jessiebobessie", "Messy");

		assertEquals(table3.findIndex(jessiebobessie), 3);

		table3.put(jessiebobessie, 2.0);
		assertEquals(table3.findIndex(jessiebobessie), 14);
	}

	@Test
	void testRemove3() {
		table3.remove(shakira);
		assertFalse(table3.containsKey(shakira));
		table3.remove(mike);
		assertFalse(table3.containsKey(mike));
		table3.remove(millie);
		assertFalse(table3.containsKey(millie));
		table3.remove(avery);
		assertFalse(table3.containsKey(avery));
		assertEquals(table3.remove(avery), null);
	}

	@Test
	void testRehash3() {
		StudentBadHash susie = new StudentBadHash(1, "Susie", "Tusie");
		table3.put(susie, 4.2);
		StudentBadHash jessiebobessie = new StudentBadHash(5, "Jessiebobessie", "Messy");
		table3.put(jessiebobessie, 2.0);
		StudentBadHash james = new StudentBadHash(3, "James", "Tames");
		table3.put(james, 2.6);

		assertEquals(table3.size(), 7);
		assertEquals(table3.findIndex(james), 21);
	}

}
