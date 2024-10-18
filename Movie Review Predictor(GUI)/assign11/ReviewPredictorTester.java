package assign11;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Review predictor tester has multiple JUnit tests for each public method in
 * the ReviewPredictor class
 * 
 * @author Madison Murray
 * @version Dec 4 2023
 */
class ReviewPredictorTester {

	ReviewPredictor review;

	@BeforeEach
	public void setup() {
		// create a new ReviewPredictor object with a sample file words.txt:
//		4 a excellent movie
//		0 a bad movie
//		3 pretty decent movie
//		3 excellent, wonderful movie
//		1 just bad.

		try {
			File filename = new File("words.txt");
			review = new ReviewPredictor(filename);
		}
		// catch a nonexistent file
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	// tests that the constructor throws appropriate exceptions for certain files
	@Test
	public void testConstructorInvalidFIle() {
		File file = new File("notReal.txt");
		assertThrows(FileNotFoundException.class, () -> {
			ReviewPredictor test = new ReviewPredictor(file);
		}, "Failed to throw exception when using an invalid file");

	}

	@Test
	public void testConstructorUnformattedFIle() {
		File file = new File("unformmated.txt");
		assertThrows(NullPointerException.class, () -> {
			ReviewPredictor test = new ReviewPredictor(file);
		}, "Failed to throw exception when using an unformatted file");

	}

	// TESTS THE PREDICT SCORE METHOD
	@Test
	public void predictScore1Word() {
		assertEquals(review.predictScore("decent"), 3);
	}

	@Test
	public void predictScoreUnmappedWords() {
		assertEquals(review.predictScore("epic film"), -2);
	}

	@Test
	public void predictScorePhrase() {
		assertEquals(review.predictScore("excellent wonderful movie"), 3);
	}

	@Test
	public void predictScorePhraseWithPunctuation() {
		assertEquals(review.predictScore("Excellent, Wonderful, Movie!"), 3);
	}

	@Test
	public void predictScoreAvgWord() {
		assertEquals(review.predictScore("a"), -1);
	}

	// TESTS THE CHECK PREDICTION METHOD

	@Test
	public void predict1Word() {
		assertEquals((review.checkPrediction("decent", 4)), 1);
	}

	@Test
	public void predictUnmappedDifference() {
		assertEquals((review.checkPrediction("epic film", 4)), -2);
	}

	@Test
	public void predictPhraseDifference() {
		assertEquals((review.checkPrediction("Excellent, Wonderful, Movie!", 4)), 1);
	}

	@Test
	public void predictNoDifference() {
		assertEquals((review.checkPrediction("decent", 3)), 0);
	}

	// TESTS THE BESTWORDS METHOD

	@Test
	public void bestWordsNormal() {
		List<String> expected = Arrays.asList("excellent", "wonderful", "pretty");
		assertEquals(review.bestWords(3), expected);
	}

	@Test
	public void bestWordsHighIndex() {
		List<String> expected = Arrays.asList("excellent", "wonderful", "pretty", "decent", "movie", "just", "bad");
		assertEquals(review.bestWords(11), expected);
	}

	@Test
	public void bestWordsNoMapped() {
		List<String> expected = new ArrayList<>(0);
		assertEquals(review.bestWords(0), expected);
	}

	@Test
	public void bestWords0Index() {
		// List<String> expected = new ArrayList<>(0);
		assertEquals(review.bestWords(-1), null);
	}

}
