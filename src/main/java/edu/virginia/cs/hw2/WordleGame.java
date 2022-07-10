package edu.virginia.cs.hw2;

import java.util.List;

/**
 * This interface describes a Wordle Game, including number of guesses made,
 * the answer, as well as the aftermath of whether the game was won or lost.
 * 
 * @author pm8fc
 *
 */

public interface WordleGame {

	/**
	 * Constant for the number of guesses possible in the WorldGame;
	 */
	int MAX_GUESSES = 6;

	/**
	 * Gets the list of guesses a player has made so far this game.
	 * @return a safe copy of a list of guesses.
	 */
	List<String> getGuesses();

	/**
	 * Gets the number of guess the player has made (the game ends after 6 guesses
	 * or the player guesses correctly).
	 * @return number of guesses
	 */
	int getNumGuesses();

	/**
	 * Gets the secret answer (target word the player is trying to guess) of the game
	 * @return answer
	 */
	String getAnswer();

	/**
	 * Checks whether the game is over because of a win. A game is a win when a player
	 * guesses the answer in 6 guesses or fewer.
	 * @return true if the game is over and is a win
	 */
	boolean isWin();

	/**
	 * Checks whether the game is over because of a loss. The game is a loss when the
	 * player does not successful guess the answer within 6 guesses.
	 * @return true if the game is over and is a loss.
	 */
	boolean isLost();

	/**
	 * Checks whether the game is over (regardless of result)
	 * @return true if the game is over
	 */
	boolean isGameOver();

	/**
	 * 	 * This method takes in a guess from the user, and returns an array of WorldResult enums. For example,
	 * if the answer were "BRAIN", and the user guessed "BASIC", the output would be:
	 * <p>
	 * [GREEN, YELLOW, GRAY, GREEN, GRAY]
	 * <p>
	 * Since the B and I are in the correct location, A is in the answer, but not in the right place, and
	 * S and C are not in the word.
	 * <p>
	 * This method updates the number of guesses made by the player. If the player runs out of guesses, the
	 * game is considered a "loss". If the player guesses the correct answer within 6 guesses, the game is
	 * considered "Won".
	 * <p>
	 * This function is *not* case sensitive, so "Brain" and "BRAIN" would be treated as matching words.
	 *
	 * @param guess - the 5 letter (A-Z) String the player is guessing
	 * @return array of enums - {@link WordleResult}
	 * @throws GameOverException            - If the player has already either won or lost the game
	 * @throws IllegalWordleAnswerException - If the player either guesses an invalid word (either incorrect length,
	 *                                      or not a word in the dictionary see
	 *                                      {@link WordleDictionary#isValidFormat(String)})
	 */

	WordleResult[] submitGuess(String guess);

}

