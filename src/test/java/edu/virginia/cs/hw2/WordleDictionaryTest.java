package edu.virginia.cs.hw2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit5 test class for the {@link WordleDictionary} class
 * 
 * @author pm8fc
 * @author [enter netid of first team member here]
 * @author [enter netid of second team member here, or delete if working alone]
 * 
 * In this file, write your White-Box unit tests for the {@link WordleDictionary} class.
 * We recommend testing and fixing {@link WordleDictionary#isValidFormat(String)} function first,
 * since all the functions below it in {@link WordleDictionary} rely on it either directly
 * or indirectly.
 * 
 * You need 100% statement coverage in {@link WordleDictionary#isValidFormat(String)} and
 * {@link WordleDictionary#addWord(String)} to get full credit.
 */

public class WordleDictionaryTest {
	
	/**
	 * The number of 5 letter words in the included dictionary resource.
	 */
	public static final int FULL_WORDLE_DICTIONARY_SIZE = 15920;
	
	/**
	 * The default dictionary of all English five-letter words
	 */
	public static FullDictionary fullDictionary;
	public static AnswersDictionary answersDictionary;
	
	
	/**
	 * Initializes the fullDictionary object
	 */
	@BeforeAll
	public static void initializeDictionary() {
		fullDictionary = FullDictionary.getInstance();
		answersDictionary = AnswersDictionary.getInstance();
	}

	@Test
	public void randomDictionaryTest() {
		System.out.println("These should look relatively random");
		for (int i = 0; i < 10; i++) {
			System.out.println(answersDictionary.getRandomAnswer());
		}
	}

	/**
	 * This tests whether or not the answers dictionary is accessible
	 * and correctly populates the dictionary with all wordle words.
	 */

	@Test
	public void loadAnswersDictionary() {
		assertNotNull(answersDictionary);
		assertEquals(2315, answersDictionary.getDictionarySize());
		assertFalse(answersDictionary.hasWord("aahed"));  // first 5 letter word, but not in answers
		assertFalse(answersDictionary.hasWord("zunis"));  //  last 5 letter word but not in answers
		assertTrue(answersDictionary.hasWord("model"));  //  middle-ish word is in answers
		assertTrue(answersDictionary.hasWord("zonal")); // last alphabetical answer
		assertTrue(answersDictionary.hasWord("aback")); // first alphatbetical answer
	}
	
	/**
	 * This tests whether or not the full dictionary is accessible
	 * and correctly populates the dictionary with all wordle words.
	 */
	
	@Test
	public void loadFullDictionary() {
		assertNotNull(fullDictionary);
		assertEquals(15920, fullDictionary.getDictionarySize());
		assertTrue(fullDictionary.hasWord("aahed"));  // first 5 letter word
		assertTrue(fullDictionary.hasWord("zunis"));  //  last 5 letter word
		assertTrue(fullDictionary.hasWord("model"));  //  middle-ish word
		assertFalse(fullDictionary.hasWord("a")); // first word, not 5 letters
		assertFalse(fullDictionary.hasWord("zwitterionic")); //last word, not 5 letters
	}

}
