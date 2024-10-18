package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SpellCheckerTest {

	SpellChecker treeSet;
	SpellChecker mySC;
	File goodluck;
	/**
	 * helper method
	 * @param sc
	 * @param documentFilename
	 */
	private static void runSpellCheck(SpellChecker sc, String documentFilename) {

		File doc = new File(documentFilename);
		List<String> misspelledWords = sc.spellCheck(doc);
		if(misspelledWords.size() == 0) 
			System.out.println("There are no misspelled words in file " + doc + ".");
		else {
			System.out.println("The misspelled words in file " + doc + " are:");
			for(String w : misspelledWords) 
				System.out.println("\t" + w);
		}
	}
	
	
	@BeforeEach
	void setup() {
		 mySC = new SpellChecker(new File("src/assign08/dictionary.txt"));
		goodluck = new File("src/assign08/good_luck.txt");
		 treeSet = new SpellChecker(new File("src/assign08/dictionary.txt"));
		
		//runSpellCheck(mySC, "src/assign08/hello_world.txt");
		//runSpellCheck(mySC, "src/assign08/good_luck.txt");
		//runSpellCheck(mySC, "src/assign08/dictionary.txt");
	}
	@Test
	void testSpellCheckFalse() {
		ArrayList<String> expected = new ArrayList<>();
		expected.add("bst");
		expected.add("cs");
		assertEquals(mySC.spellCheck(goodluck).get(0), expected.get(0));
		assertEquals(mySC.spellCheck(goodluck).get(1), expected.get(1));

	}
	
	@Test
	void testSpellCheckFalseTS() {
		ArrayList<String> expected = new ArrayList<>();
		expected.add("bst");
		expected.add("cs");
		assertEquals(treeSet.spellCheck(goodluck).get(0), expected.get(0));
		assertEquals(treeSet.spellCheck(goodluck).get(1), expected.get(1));

	}
	
	void testAddtoDictionary() {
		mySC.addToDictionary("cs");
		assertEquals(mySC.spellCheck(goodluck).size(), 1);
		mySC.addToDictionary("bst");
		assertEquals(mySC.spellCheck(goodluck).size(), 0);
	}
	
	void testAddtoDictionaryTS() {
		treeSet.addToDictionary("cs");
		assertEquals(treeSet.spellCheck(goodluck).size(), 1);
		treeSet.addToDictionary("bst");
		assertEquals(treeSet.spellCheck(goodluck).size(), 0);
	}
	
	void testRemove() {
		mySC.removeFromDictionary("careful");
		mySC.addToDictionary("cs");
		mySC.addToDictionary("bst");
		assertEquals(mySC.spellCheck(goodluck).size(), 1);
		ArrayList<String> expected = new ArrayList<>();
		expected.add("careful");
		assertEquals(mySC.spellCheck(goodluck).get(0), expected.get(0));
		
	}
	
	void testRemoveDoesntExist() {
		mySC.removeFromDictionary("cs");
	}
	
	void testRemoveTS() {
		treeSet.removeFromDictionary("careful");
		treeSet.addToDictionary("cs");
		treeSet.addToDictionary("bst");
		assertEquals(treeSet.spellCheck(goodluck).size(), 1);
		ArrayList<String> expected = new ArrayList<>();
		expected.add("careful");
		assertEquals(treeSet.spellCheck(goodluck).get(0), expected.get(0));
		
	}
	
	void testDuplicates() {
		mySC.addToDictionary("cs");
		mySC.addToDictionary("bst");
		mySC.removeFromDictionary("are");
		assertEquals(mySC.spellCheck(goodluck).size(), 1);
		
	}
	
	void testDuplicatesTS() {
		treeSet.addToDictionary("cs");
		treeSet.addToDictionary("bst");
		treeSet.removeFromDictionary("are");
		assertEquals(treeSet.spellCheck(goodluck).size(), 1);
		
	}
	

}
