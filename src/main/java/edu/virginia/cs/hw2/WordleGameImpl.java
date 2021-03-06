package edu.virginia.cs.hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * This class maintains the Wordle Game State, including number of guesses made,
 * the answer, the dictionary of valid guesses, as well as the aftermath of
 * whether or not the games was won.
 *
 * @author pm8fc
 *
 * SDE Students: you will be doing Black-Box testing on the submitGuess(String) method.
 *
 */

public class WordleGameImpl implements WordleGame {

    // SDE Students - You may not modify or remove these fields. Additionally, do not add any
    // more fields then what you are given here

    /**
     * The number of total guesses the player has made (game ends after 6 guesses)
     */
    protected List<String> guesses;

    /**
     * The word the player is trying to guess
     */
    protected final String answer; //the word the player is trying to guess

    /**
     * A dictionary of valid 5-letter words
     */
    protected final WordleDictionary dictionary;

    /**
     * Flag that is true if player has already won the game (guessed the correct answer), false otherwise.
     */
    protected boolean hasWon;

    /**
     * Flag that is true if player has already lost the game (out of guesses), false otherwise.
     */
    protected boolean hasLost; // true if player has lost the game, making 6 guesses
    // without guessing correctly


    /**
     * Constructor for Wordle Game State
     * @param dictionary - the Wordle dictionary of valid words to use for this game
     * @param answer - the word the player is trying to guess
     * @throws IllegalWordleAnswerException when answer is not in dictionary
     */
    protected WordleGameImpl(WordleDictionary dictionary, String answer) {
        if (!dictionary.hasWord(answer)) {
            throw new IllegalWordleAnswerException("Error! Your answer isn't in the dictionary!");
        }

        this.dictionary = dictionary;
        this.answer = answer;

        guesses = new ArrayList<>();
        hasWon = false;
        hasLost = false;
    }

    protected WordleGameImpl() {
        this(FullDictionary.getInstance(), AnswersDictionary.getInstance().getRandomAnswer());
    }

    public boolean isGameOver() {
        return hasWon || hasLost;
    }

    public List<String> getGuesses() {
        return new ArrayList<>(guesses);
    }

    public int getNumGuesses() {
        return guesses.size();
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isWin() {
        return hasWon;
    }

    public boolean isLost() {
        return hasLost;
    }

    /**
     * SDE Students: <strong>do not implement this method!</strong> Simply write tests for it!
     *
     * This method takes in a guess from the user, and returns an array of WorldResult enums. For example,
     * if the answer were "BRAIN", and the user guessed "BASIC", the output would be:
     *
     *    [GREEN, YELLOW, GRAY, GREEN, GRAY]
     *
     * Since the B and I are in the correct location, A is in the answer, but not in the right place, and
     * S and C are not in the word.
     *
     * This method updates to keep track of the number of guesses. If the player runs out of guesses, it
     * says the hasLost flag to true. If the player guesses the correct answer within 6 guesses, the
     * function says the hasWon flag to true.
     *
     * This function is *not* case sensitive, so "Brain" and "BRAIN" would be treated as matching words.
     *
     * @param guess - the 5 letter (A-Z) String the player is guessing
     * @throws GameOverException - If the player has already either won or lost the game
     * @throws IllegalWordleAnswerException - If the player either guesses an invalid word (either incorrect length,
     * or not a word in the dictionary see {@link WordleDictionary#isValidFormat(String)})
     * @return array of enums - {@link WordleResult}
     */

    public WordleResult[] submitGuess(String guess) {
        if (hasWon || hasLost) {
            throw new GameOverException("Error: This World Game is already over");
        }

        if (!dictionary.hasWord(guess)) {
            throw new IllegalWordleAnswerException("Error: that is not a valid word in the dictionary.");
        }

        guess = guess.toUpperCase(); //make sure guess is uppercase

        guesses.add(guess); //add to list of guesses

        //if correct
        if (isCorrectAnswer(guess)) { //guessed the answer
            hasWon = true;
            return generateCorrectArray();
        } else if (guesses.size() == MAX_GUESSES) { //all guesses used
            hasLost = true;
        }
        return getGuessResult(guess, answer);
    }

    private boolean isCorrectAnswer(String guess) {
        return guess.equals(answer);
    }

    private WordleResult[] generateCorrectArray() {
        WordleResult[] result = new WordleResult[5];
        for(int i = 0; i < WordleDictionary.WORD_LENGTH; i++) {
            result[i] = WordleResult.GREEN;
        }
        return result;
    }

    protected static WordleResult[] getGuessResult(String guess, String answer) {

        //eventual return value
        WordleResult[] result = new WordleResult[5];

        //keeps track of which letters in the answer have been used
        boolean[] letterUsed = new boolean[5];

        //initialize to all gray, unless the letters match, then green
        getGreens(guess, answer, result, letterUsed);

        //check for yellow letters
        for (int i = 0; i < 5; i++) {
            if (result[i] == WordleResult.GREEN) { //skip green letters
                continue;
            }
            getYellows(guess, answer, result, letterUsed, i);
        }

        return result;
    }

    private static void getYellows(String guess, String answer, WordleResult[] result, boolean[] letterUsed, int index) {
        char guessLetter = guess.charAt(index);
        for (int j = 0; j < 5; j++) {
            if (index == j || letterUsed[j]) { //if answer letter accounted for already, skip
                continue;
            }
            char answerLetter = answer.charAt(j);
            if (guessLetter == answerLetter) { //if these two letters at different locations match
                result[index] = WordleResult.YELLOW;
                letterUsed[j] = true;
                break;
            }
        }
    }

    private static void getGreens(String guess, String answer, WordleResult[] result, boolean[] letterUsed) {
        for(int i = 0; i < 5; i++) {
            result[i] = WordleResult.GRAY;
            if (guess.charAt(i) == answer.charAt(i)) {
                result[i] = WordleResult.GREEN;
                letterUsed[i] = true;
            }
        }
    }

}


