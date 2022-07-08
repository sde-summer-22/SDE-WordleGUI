package edu.virginia.cs.hw2;

/**
 * An Exception generated when trying to create a Wordle game with a word
 * that isn't valid to the Wordle dictionary, or attempting to guess a word
 * that isn't a valid Wordle dictionary word.
 * 
 * @author pm8fc
 */

@SuppressWarnings("serial")
public class IllegalWordleAnswerException extends RuntimeException {

	/**
	 * Simple constructor for exception
	 * @param message - error message
	 */
	public IllegalWordleAnswerException(String message) {
		super(message);
	}

}
