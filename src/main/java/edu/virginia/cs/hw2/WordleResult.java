
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
 * Each color prints as the color it represents on a command line.
 *
 */

public enum WordleResult {
	/**
	 * GRAY result, this letter isn't in the word
	 */
	GRAY {
		@Override
		public String toString() {
			return ("\033[0;37m" + super.toString() + "\033[0m");
		}
	},
	
	/**
	 * GREEN result, this letter is in the word at this location
	 */
	GREEN {
		@Override
		public String toString() {
			return ("\033[0;32m" + super.toString() + "\033[0m");
		}
	},
	
	/**
	 * YELLOW result, this letter is in the word, but not at this location
	 */
	YELLOW {
		@Override
		public String toString() {
			return ("\033[0;33m" + super.toString() + "\033[0m");
		}
	},
}
