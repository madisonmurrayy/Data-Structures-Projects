package assign11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * This class predicts the rating of a certain review on a scale of 1-4, ans
 * uses this rating to then compare to other reviews/words and ratings.
 * 
 * @author Madison Murray
 * @version Dec 4, 2023
 */
public class ReviewPredictor {

	private String[] currentWords; // holds array of strings in a line

	private ScoreCalculator scoreCalc;// creates instance of nested class ScoreCalculator to reference its methods

	/**
	 * constructor method for the Review Predictor class
	 * 
	 * @param reviewsFile to be scanned and read through
	 * @throws FileNotFoundException
	 */
	public ReviewPredictor(File reviewsFile) throws FileNotFoundException {

		// reset score calculator variables when a new file is uploaded
		scoreCalc = new ScoreCalculator();
		scoreCalc.allKeys.clear();
		scoreCalc.reviews.clear();
		int tempReviewValue;// holds review number at front of line

		// try to read a valid file
		try (Scanner input = new Scanner(reviewsFile)) {

			String currentLine;// initialize currentLine before loop

			// while there is content on the next line
			while (input.hasNext()) {

				int index = 0; // index of array counter

				// get input from current line
				currentLine = input.nextLine();
				currentLine = currentLine.toLowerCase().replaceAll("[^a-z0-9 ]", "");

				// split input into an array
				currentWords = currentLine.split(" ");

				// set the first int as the next element in the array
				try {
					tempReviewValue = Integer.parseInt(currentWords[0]);
				}

				// catch an unformatted file
				catch (NumberFormatException e) {
					throw new NullPointerException();
				}

				// for each element in currentWords after 0, create as a key
				// to temp review value, if key doesnt exist
				for (String word : currentWords) {
					// update the map in ScoreCalculator with this word and its value
					scoreCalc.updateMap(word, tempReviewValue, index);
					index++;// increase index for updateMap method
				}
			}
		}

		// catch a non existent file
		catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
	}

	/**
	 * This is a public getter method to obtain the current status of the uploaded
	 * file in order to keep the GUI updated
	 * 
	 * @return boolean status to represent file status
	 */
	public boolean goodFile() {
		return this.goodFile();
	}

	/**
	 * Predicts the score of a string of text entered by the user
	 * 
	 * @param reviewTextentered by the user - text to be reviewed
	 * @return the predicted score value
	 */
	public double predictScore(String reviewText) {
		// match text to the format from the file
		reviewText = reviewText.toLowerCase().replaceAll("[^a-z0-9 ]", "");
		currentWords = reviewText.split(" ");
		// call the getScore method
		return scoreCalc.getScore(currentWords);
	}

	/**
	 * Predicts the score of an entered text and finds the difference between that
	 * calculated score and the actual score- also entered by the user
	 * 
	 * @param reviewText String entered by the user to be calculated
	 * @param actual     score entered by the user to be compared to calculated
	 *                   score
	 * @return the difference between the calculated score and the actual score
	 */
	public double checkPrediction(String reviewText, double score) {
		// if the word doesnt have a score(returns -1) then return -1
		if ((predictScore(reviewText)) < 0) {
			return (predictScore(reviewText));
		}
		// else, return difference
		return Math.abs(predictScore(reviewText) - score);
	}

	/**
	 * Creates a string of a certain number of best ranked/rated words in the review
	 * and returns them in a list
	 * 
	 * @param int k tells how many words should be ranked
	 * @return a List of k best ranked Strings
	 */
	public List<String> bestWords(int k) {

		// if k is out of bounds
		if ((k < 0) || (k >= Integer.MAX_VALUE)) {
			return null;
		}

		else {
			// create List from a Set of all Keys
			List<String> allKeysList = new ArrayList<>(scoreCalc.allKeys);
			// sort the allKeysList with the implemented compare method
			Collections.sort(allKeysList, new OrderByPredictedScore());

			// if k is less than the list size, set to one less than size
			if (k >= allKeysList.size()) {
				k = allKeysList.size() - 1;
			}

			// loop through all elements of the list
			for (int i = 0; i < allKeysList.size(); i++) {
				// if the predicted score of the word is in the 1.75-2.25 range, remove it from
				// the list
				if (scoreCalc.getSingleWordScore(allKeysList.get(i)) > 1.75
						&& scoreCalc.getSingleWordScore(allKeysList.get(i)) < 2.25) {

					allKeysList.remove(allKeysList.get(i));
				}
			}

			// sort list and reverse
		//	Collections.sort(allKeysList, new OrderByPredictedScore());
			//Collections.reverse(allKeysList);
			return allKeysList.subList(0, k);
		}
	}

	/**
	 * Implements comparator to be used in the best String method, sorting first by
	 * rating from highest to lowest, then lexigraphically
	 */
	public class OrderByPredictedScore implements Comparator<String> {
		@Override

		public int compare(String s1, String s2) {
			System.out.println("comparing "+ s1 + " to " + s2);
			// orders strings from highest rating to lowest score
			double difference = scoreCalc.getSingleWordScore(s1) - scoreCalc.getSingleWordScore(s2);
			if (difference < 0) {
				return -1;
			}
			if (difference > 0) {
				return 1;
			}
			// if difference is equal, order lexigraphically
			//System.out.println(s2.compareTo(s1));
			return s1.compareTo(s2);
		}

	}

	// nesting score calculator
	/**
	 * Private nested class inside of Review Predictor to do most of the math and
	 * loops, methods used multiple times to do calculations
	 * 
	 * @author Madison Murray
	 * @versopm Dec 4, 2023
	 */
	private class ScoreCalculator {
		// Create a HashMap to map each unique word to an array of its associated review
		// integers
		public Map<String, ArrayList<Integer>> reviews = new HashMap<>();
		// Create a Set to hold all keys in order to assure no duplicates
		public Set<String> allKeys = new HashSet<>();
		// create an array to act as the value of each unique keu
		private ArrayList<Integer> allScores;

		/**
		 * Update map method is called from the constructor to create a map based on the
		 * current uploaded file and the values it passes through to parameters
		 * 
		 * @param String current word on the current review
		 * @param score  corresponding to the current review line
		 * @param index  holding the current index of word position on the line
		 */
		public void updateMap(String word, int score, int index) {

			// put every word into a Set for later reference(wont add duplicates)

			// if the word if the first element from the line,(the rating), we should skip
			// it
			if (index == 0) {
				return;
			}

			// put every word into a Set for later reference(wont add duplicates)
			allKeys.add(word);

			// if the map already has this word as a key
			if (reviews.containsKey(word)) {

				// update the array of all scores mapped to this word
				allScores = reviews.get(word);
				allScores.add(score);
				reviews.replace(word, allScores);
				return;
			}

			// if this word doesn't exist as a key yet, make it one and initialize the value
			// array
			allScores = new ArrayList<>();
			allScores.add(score);
			reviews.put(word, allScores);

		}

		/**
		 * This is a helper method the get the predicted score of a single word from the
		 * array
		 * 
		 * @param word to get the score of
		 * @return the predicted value of score
		 */
		public double getSingleWordScore(String word) {

			double total;// holds total of array values
			double scoreCount;// holds number of values in array

			// reset count variables for each word
			total = 0;
			scoreCount = 0;

			// if the word exists in the map
			if (reviews.containsKey(word)) {

				// find the array of reviews that are associated with that word and loop through
				// it
				for (Integer score : reviews.get(word)) {

					// convert integer to double and add to to total
					total += (1.0 * score);

					// increase score count
					scoreCount++;
				}
				// after looping through all recorded scores for this word, average them and add
				// to wordsScores
				return total / scoreCount;
			}
			// if the word doesn't exist in the map, return -1
			return -1;
		}

		/**
		 * gets the total score of any String of word(s) passed through
		 * 
		 * @param words array of words to be scored and averaged together
		 * @return double value of total average sentence value
		 */
		public double getScore(String[] words) {
			// counts the total score of the viable words in the array
			double wordsScoresTotal = 0;
			// counts the total number of viable words in the array
			double wordsScoresCount = 0;
			// counts the number of words not use (words between 1.75 and 2.25
			double averageWords = 0;
			// array of doubles holds all of the existing words
			ArrayList<Double> wordsScores = new ArrayList<>();

			// for each word in the string passed through
			for (String word : words) {

				// if word exists, add to wordsScores
				if (!(getSingleWordScore(word) == -1)) {
					System.out.println("word exists");
					wordsScores.add(getSingleWordScore(word));
				}

			}

			// after looping through sentence, find the average score of all mapped words
			for (Double wordScore : wordsScores) {

				// only add to total if it isn't in range [1.75, 2,25]
				if (!((wordScore > 1.75) && (wordScore < 2.25))) {
					wordsScoresTotal += wordScore;
					wordsScoresCount++;

				}
				// if it is in that range, add it to count of average words
				else {
					averageWords++;
				}
			}

			// if one word exists and its average, return -1
			if (wordsScores.size() == 1 && averageWords == 1) {
				return -1;
			}
			// if no words were mapped, return -2 to get GUI know that there is a unique
			// message to display
			if (wordsScores.size() == 0) {
				return -2;
			}

			// return the total score of all mapped words divided by the number of mapped
			// words
			return wordsScoresTotal / (wordsScoresCount);
		}

	}

}
