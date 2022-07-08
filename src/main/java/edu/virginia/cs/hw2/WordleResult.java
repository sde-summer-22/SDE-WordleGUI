
package edu.virginia.cs.hw2;

/**
 * Enumerated type for Wordle
 * 
 * @author pm8fc
 * 
 * 
 * In your code, use WordleResult.GRAY, WordleResult.GREEN, and WordleResult.YELLOW
 * as though they were primitive data types.
 * 
 * {@link GRAY}
 * {@link GREEN}
 * {@link YELLOW}
 *
 */

public enum WordleResult {
	/**
	 * GRAY result, this letter isn't in the word
	 */
	GRAY,   
	
	/**
	 * GREEN result, this letter is in the word at this location
	 */
	GREEN,  
	
	/**
	 * YELLOW result, this letter is in the word, but not at this location
	 */
	YELLOW, 
}
