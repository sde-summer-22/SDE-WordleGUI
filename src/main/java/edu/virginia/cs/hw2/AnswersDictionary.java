package edu.virginia.cs.hw2;

import java.io.InputStream;
import java.util.Iterator;

public class AnswersDictionary extends WordleDictionary {
    private static AnswersDictionary instance; // The singleton instance
    private boolean canAdd; // When true, disables adding new words to the dictionary

    private static final String ANSWERS_DICTIONARY_FILENAME = "dictionaries/answers_dictionary.txt";
    private static final String MODIFICATON_ERROR_MESSAGE = "ERROR: you may not modify the default answers dictionary.\nIf you"
            + " wish to use a modified dictionary, you must make your own dictionary!";


    /**
     * Singleton constructor. Private so it can only be envoked internally.
     * Loads the Full Dictionary file into an input stream and adds the words.
     */

    private AnswersDictionary() {
        canAdd = true;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(ANSWERS_DICTIONARY_FILENAME);
        super.addWordsFromFile(is);
        canAdd = false;
    }

    public static AnswersDictionary getInstance() {
        if (instance == null) {
            instance = new AnswersDictionary();
        }
        return instance;
    }

    @Override
    public boolean addWord(String word) {
        if (!canAdd) {
            throw new DictionaryModificationExeception(MODIFICATON_ERROR_MESSAGE);
        }
        return super.addWord(word);
    }

    public String getRandomAnswer() {
        return CollectionFunctions.getRandomItemFromSet(validWords);
    }
}
