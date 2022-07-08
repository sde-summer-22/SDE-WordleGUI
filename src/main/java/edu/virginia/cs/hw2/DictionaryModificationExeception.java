package edu.virginia.cs.hw2;

/**
 * An Exception generated when trying to add words to the full dictionary singleton object.
 * 
 * @author pm8fc
 */


public class DictionaryModificationExeception extends RuntimeException {
	/**
	 * Simple constructor for exception
	 * @param message - error message
	 */
	public DictionaryModificationExeception(String message) {
		super(message);
	}

}
