package edu.virginia.cs.hw2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for WordleDictionary.
 * 
 * SDE Students: you will use this file for BlackBox-Testing of the {@link WordleGame#submitGuess(String word)} function.
 * 
 * @author pm8fc
 * @author [enter netid of first team member here]
 * @author [enter netid of second team member here, or delete if working alone]
 * 
 */

public class WordleGameStateTest {

	private static final WordleResult GREEN = WordleResult.GREEN;
	private static final WordleResult YELLOW = WordleResult.YELLOW;
	private static final WordleResult GRAY = WordleResult.GRAY;
	
	/**
	 * Full Dictionary useful for testing with all 5-letter English words
	 */
	private static WordleDictionary fullDictionary;
	
	//If you wish to create any instance testing variables, do so here.

	private WordleGame gameState;
	
	/**
	 * Loads the full dictionary from src/test/resources
	 */
	@BeforeAll
	public static void loadFullDictionary() {
		fullDictionary = FullDictionary.getInstance();
	}

	
	/**
	 * If you wish to write a setup "BeforeEach" function, do so here:
	 */
	

	@BeforeEach
	public void setup() {
		gameState = new WordleGame(fullDictionary, "MATCH");
	}

	
	
	/**
	 * Ensure fullDictionary correctly loads and can be used in a game state, and check to
	 * ensure all fields are correctly initialized.
	 */
	
	@Test
	public void testInitFullDictionary() {
		WordleGame initGameState = new WordleGame(fullDictionary, "BRAIN");
		assertEquals(fullDictionary, initGameState.dictionary);
		assertEquals(WordleDictionaryTest.FULL_WORDLE_DICTIONARY_SIZE, initGameState.dictionary.getDictionarySize());
		assertEquals("BRAIN", initGameState.answer);
		assertEquals(0, initGameState.getNumGuesses());
		assertFalse(initGameState.hasWon);
		assertFalse(initGameState.hasLost);
	}
	
	/**
	 * Ensures that words that you cannot create a game with an answer not in the dictionary.
	 */
	@Test
	public void testInitFullDictionaryInvalidAnswer() {
		assertThrows(IllegalWordleAnswerException.class, () ->
				new WordleGame(fullDictionary, "QZYXY"));
	}

	@Test
	public void getResultCorrect() {
		WordleResult[] result = WordleGame.getGuessResult("MATCH", "MATCH");
		WordleResult[] expected = {GREEN, GREEN, GREEN, GREEN, GREEN};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultCompletelyIncorrect() {
		WordleResult[] result = WordleGame.getGuessResult("BIKER", "MATCH");
		WordleResult[] expected = {GRAY, GRAY, GRAY, GRAY, GRAY};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultAllYellow() {
		WordleResult[] result = WordleGame.getGuessResult("ELBOW", "BOWEL");
		WordleResult[] expected = {YELLOW, YELLOW, YELLOW, YELLOW, YELLOW};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultMixGreenYellow() {
		WordleResult[] result = WordleGame.getGuessResult("BELOW", "BOWEL");
		WordleResult[] expected = {GREEN, YELLOW, YELLOW, YELLOW, YELLOW};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultMixAll() {
		WordleResult[] result = WordleGame.getGuessResult("BLAST", "BOWEL");
		WordleResult[] expected = {GREEN, YELLOW, GRAY, GRAY, GRAY};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultDoubleLetterCorrect() {
		WordleResult[] result = WordleGame.getGuessResult("METER", "METER");
		WordleResult[] expected = {GREEN, GREEN, GREEN, GREEN, GREEN};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultDoubleLetterAnswerOneLetterGuess() {
		WordleResult[] result = WordleGame.getGuessResult("TREND", "METER");
		WordleResult[] expected = {YELLOW, YELLOW, YELLOW, GRAY, GRAY};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultOneGreenLetterAnswerDoubleLetterGuess() {
		WordleResult[] result = WordleGame.getGuessResult("METER", "METRO");
		WordleResult[] expected = {GREEN, GREEN, GREEN, GRAY, YELLOW};
		assertArrayEquals(expected, result);
	}

	@Test
	public void getResultOneYellowLetterAnswerDoubleLetterGuess() {
		WordleResult[] result = WordleGame.getGuessResult("METER", "THINE");
		WordleResult[] expected = {GRAY, YELLOW, YELLOW, GRAY, GRAY};
		assertArrayEquals(expected, result);
	}

	@Test
	public void tripleLetterAnswerWithGreenYellowGrayGuess() {
		WordleResult[] result = WordleGame.getGuessResult("GEESE", "ELITE");
		WordleResult[] expected = {GRAY, YELLOW, GRAY, GRAY, GREEN};
		assertArrayEquals(expected, result);
	}

	/**
	 * Correct guess on first word
	 */
	@Test
	public void submitGuessFirstGuessCorrect() {
		WordleResult[] result = gameState.submitGuess("MATCH");
		WordleResult[] expected = {GREEN, GREEN, GREEN, GREEN, GREEN};
		assertArrayEquals(expected, result);
		assertEquals(1, gameState.getNumGuesses());
		assertTrue(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void submitGuessFirstGuessInCorrect() {
		WordleResult[] result = gameState.submitGuess("BIKER");
		WordleResult[] expected = {GRAY, GRAY, GRAY, GRAY, GRAY};
		assertArrayEquals(expected, result);
		assertEquals(1, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void submitGuessFirstGuessMixed() {
		WordleResult[] result = gameState.submitGuess("VALET");
		WordleResult[] expected = {GRAY, GREEN, GRAY, GRAY, YELLOW};
		assertArrayEquals(expected, result);
		assertEquals(1, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void submitGuessInvalidWordTooLong() {
		assertThrows(IllegalWordleAnswerException.class, () ->
			gameState.submitGuess("BALLOT")
		);
		assertEquals(0, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void submitGuessInvalidWordTooShort() {
		assertThrows(IllegalWordleAnswerException.class, () ->
				gameState.submitGuess("HAM")
		);
		assertEquals(0, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}
	@Test
	public void submitGuessInvalidWordNotAWord() {
		assertThrows(IllegalWordleAnswerException.class, () ->
				gameState.submitGuess("ZFIDF")
		);
		assertEquals(0, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void submitGuessThirdGuessWinner() {
		for(int i = 0; i < 3; i++) //ensure 3 guesses already
			gameState.guesses.add("GUESS");
		WordleResult[] result = gameState.submitGuess("MATCH");
		WordleResult[] expected = {GREEN, GREEN, GREEN, GREEN, GREEN};
		assertArrayEquals(expected, result);
		assertEquals(4, gameState.getNumGuesses());
		assertTrue(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void submitGuessThirdGuessNonWinner() {
		for(int i = 0; i < 3; i++) //ensure 3 guesses already
			gameState.guesses.add("GUESS");
		WordleResult[] result = gameState.submitGuess("BATCH");
		WordleResult[] expected = {GRAY, GREEN, GREEN, GREEN, GREEN};
		assertArrayEquals(expected, result);
		assertEquals(4, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void lastGuessWinner() {
		for(int i = 0; i < 5; i++) //ensure 3 guesses already
			gameState.guesses.add("GUESS");
		WordleResult[] result = gameState.submitGuess("MATCH");
		WordleResult[] expected = {GREEN, GREEN, GREEN, GREEN, GREEN};
		assertArrayEquals(expected, result);
		assertEquals(6, gameState.getNumGuesses());
		assertTrue(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void lastGuessLoser() {
		for(int i = 0; i < 5; i++) //ensure 5 guesses already
			gameState.guesses.add("GUESS");
		WordleResult[] result = gameState.submitGuess("BATCH");
		WordleResult[] expected = {GRAY, GREEN, GREEN, GREEN, GREEN};
		assertArrayEquals(expected, result);
		assertEquals(6, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertTrue(gameState.hasLost);
	}

	@Test
	public void guessCorrectAfterEarlyWinner() {
		for(int i = 0; i < 1; i++) //ensure 1 guesses already
			gameState.guesses.add("GUESS");
		gameState.hasWon = true;
		assertThrows(GameOverException.class, () ->
				gameState.submitGuess("MATCH")
		);
		assertEquals(1, gameState.getNumGuesses());
		assertTrue(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void guessCorrectAfterLateWinner() {
		for(int i = 0; i < 6; i++) //ensure 6 guesses already
			gameState.guesses.add("GUESS");
		gameState.hasWon = true;
		assertThrows(GameOverException.class, () ->
				gameState.submitGuess("MATCH")
		);
		assertEquals(6, gameState.getNumGuesses());
		assertTrue(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void guessIncorrectAfterEarlyWinner() {
		for(int i = 0; i < 1; i++) //ensure 1 guesses already
			gameState.guesses.add("GUESS");
		gameState.hasWon = true;
		assertThrows(GameOverException.class, () ->
				gameState.submitGuess("BATCH")
		);
		assertEquals(1, gameState.getNumGuesses());
		assertTrue(gameState.hasWon);
		assertFalse(gameState.hasLost);
	}

	@Test
	public void guessCorrectAfterLoser() {
		for(int i = 0; i < 6; i++) //ensure 6 guesses already
			gameState.guesses.add("GUESS");
		gameState.hasLost = true;
		assertThrows(GameOverException.class, () ->
				gameState.submitGuess("MATCH")
		);
		assertEquals(6, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertTrue(gameState.hasLost);
	}

	@Test
	public void guessIncorrectAfterLoser() {
		for(int i = 0; i < 6; i++) //ensure 6 guesses already
			gameState.guesses.add("GUESS");
		gameState.hasLost = true;
		assertThrows(GameOverException.class, () ->
				gameState.submitGuess("BATCH")
		);
		assertEquals(6, gameState.getNumGuesses());
		assertFalse(gameState.hasWon);
		assertTrue(gameState.hasLost);
	}
}
