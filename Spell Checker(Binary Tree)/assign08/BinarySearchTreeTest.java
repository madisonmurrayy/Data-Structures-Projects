package assign08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	BinarySearchTree<Integer> ints = new BinarySearchTree<>();
	@Test
	void test() {
		ints.add(2);
		ints.add(3);
		assertFalse(ints.isEmpty());
		ints.remove(2);
		ints.remove(3);
		assertTrue(ints.isEmpty());
	}

}
