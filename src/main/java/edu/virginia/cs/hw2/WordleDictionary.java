package edu.virginia.cs.hw2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * This class models a dictionary specifically for Wordle. This means all
 * the words in this dictionary are 5 letters long. For simplicity, all the words
 * are stored in UPPERCASE letters only.
 *
 * @author pm8fc
 * @author [enter netid of first team member here]
 * @author [enter netid of second team member here, or delete if working alone]
 *
 * <strong>SDE Students:</strong> You must write tests that achieve 100% statement coverage of the
 * code below. <strong>You must have a commit with your white-box tests *before* your commit
 * with your bug fixes</strong> to the {@link WordleDictionary#isValidFormat(String)} function.
 */

public class WordleDictionary {

    public final static int WORD_LENGTH = 5;

    /**
     * Set of words accepted by the dictionary
     */
    protected Set<String> validWords;

    /**
     * Constructor, initializes the empty set of words.
     */
    public WordleDictionary() {
        validWords = new HashSet<>();
    }

    /**
     * Gets the number of words in the dictionary set
     *
     * @return dictionary's size
     */
    public int getDictionarySize() {
        return validWords.size();
    }

    /**
     * Checks whether a given word is in the dictionary. Note that this
     * function is case insensitive.
     *
     * @param s - the world to be checked
     * @return true if the word is present in the dictionary, false otherwise
     */
    public boolean hasWord(String s) {
        return validWords.contains(s.toUpperCase());
    }

    /**
     * <strong>SDE Students: This method has bugs! In addition to testing the method, you
     * must fix the bugs! </strong>
     * <p>
     * Checks if a word is a valid Wordle format. The word is valid if and only if:
     * - it is a 5 letter word
     * - it is made up of only A-Z letters
     * <p>
     * This function is case insensitive, and should work with the input being
     * both upper *and* lower case (as well as any combination thereof).
     *
     * @param word - a String to be checked for valid World format
     * @return true if the String is valid, false if it is not valid
     */
    protected static boolean isValidFormat(String word) {
        if (word == null || word.length() != WORD_LENGTH) {
            return false;
        }
        return isOnlyLetters(word);
    }

    private static boolean isOnlyLetters(String word) {
        word = word.toUpperCase();
        for (int i = 0; i < 5; i++) {
            if (isCapitalLetter(word.charAt(i))) return false;
        }
        return true;
    }

    private static boolean isCapitalLetter(char ch) {
        return (ch < 'A' || ch > 'Z');
    }


    /**
     * Adds a valid Wordle word to the dictionary set
     *
     * @param word - the valid Wordle word to be added to the dictionary
     * @return true if the word is added, returns false if the word wasn't
     * \added because it was already in the dictionary
     * @throws IllegalArgumentException - if word is not a valid Wordle word
     */
    public boolean addWord(String word) {
        if (!isValidFormat(word)) {
            throw new IllegalArgumentException("Error: the word " + word +
                    " is not a 5-letter alphabetical word");
        }
        return validWords.add(word.toUpperCase());
    }

    /**
     * Takes in a string with, ideally with one word on each line, and
     * adds each valid line to the dictionary.
     *
     * @param words - a multiline string of words to be added to the dictionary
     *              Example:
     *              "BOAST
     *              CHIDE
     *              APPLE
     *              FLUNK"
     */
    public void addWords(String words) {
        addWords(Arrays.stream(words.split("\n")));
    }


    /**
     * Takes in a stream of words and adds all valid words to
     *
     * @param words - a stream of Strings, where each string is a potential wordle word
     */
    public void addWords(Stream<String> words) {
        words.filter(WordleDictionary::isValidFormat)
                .forEach(this::addWord);
    }

    /**
     * Takes in a dictionary simple text filename and adds all valid words to the dictionary.
     * <p>
     * File should be formatted as a simple text file with one word on each line. If there
     * are multiple tokens on a single line, they will be treated as one word.
     *
     * @param filename - the name (i.e. location) of the text file
     * @throws FileNotFoundException - If filename is not a valid file
     * @throws IOException           - IO Error when reading file
     */
    public void addWordsFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8));
        addWordsFromFile(br);
    }

    /**
     * Takes in a dictionary simple text file as an InputStream and adds all valid words
     * to the dictionary.
     * <p>
     * File should be formatted as a simple text file with one word on each line. If there
     * are multiple tokens on a single line, they will be treated as one word.
     *
     * @param dictionaryFileStream - InputStream of a simple text file containing words
     */

    public void addWordsFromFile(InputStream dictionaryFileStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(dictionaryFileStream));
        addWordsFromFile(br);
    }

    /**
     * Private helper method for addWordsFromFile(String) and addWordsFromFile(InputStream)
     *
     * @param br - input BufferedReader - BufferedReader generate from input text source
     */

    private void addWordsFromFile(BufferedReader br) {
        addWords(br.lines());
    }

}
