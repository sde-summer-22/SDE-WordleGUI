package edu.virginia.cs.hw2;

import java.io.InputStream;

/**
 * Singleton class used for getting a full dictionary of every English word with
 * 5 letters. Pulls from the existing dictionary resource file.
 * 
 * @author pm8fc
 */

public class FullDictionary extends WordleDictionary {
	private static FullDictionary instance; // The singleton instance
	private boolean canAdd; // When true, disables adding new words to the dictionary
	
	private static final String FULL_DICTIONARY_FILENAME = "dictionaries/dictionary.txt";
	private static final String MODIFICATON_ERROR_MESSAGE = "ERROR: you may not modify the default full dictionary.\nIf you"
			+ " wish to use a modified dictionary, you must make your own dictionary!";
	
	
	/**
	 * Singleton constructor. Private so it can only be envoked internally.
	 * Loads the Full Dictionary file into an input stream and adds the words.
	 */
	
	private FullDictionary() {
		canAdd = true;
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(FULL_DICTIONARY_FILENAME);
		super.addWordsFromFile(is);
		canAdd = false;
	}
	
	/**
	 * Standard singleton getInstance method in order to get the Full Dictionary instance.
	 * @return the singleton instance, creating it if it doesn't exist yet.
	 */
	public static FullDictionary getInstance() {
		if (instance == null) {
			instance = new FullDictionary();
		}
		return instance;
	}
	
	/**
	 * Disallows adding new words after the dictionary is full.
	 */
	
	@Override
	public boolean addWord(String word) {
		if (!canAdd) {
			throw new DictionaryModificationExeception(MODIFICATON_ERROR_MESSAGE);
		}
		return super.addWord(word);
	}
	
}
