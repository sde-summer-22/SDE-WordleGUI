package edu.virginia.cs.hw2;

import java.util.Arrays;
import java.util.Scanner;

public class WordlePlay {
    private static Scanner in;

    public static void main(String[] args) {
        System.out.println("Welcome to WordleSDE: Command Line Edition");
        WordlePlay wp = new WordlePlay();
        wp.init();
        do {
            wp.run();
        } while (playAgain());

        System.out.println("Thank you for playing!");

        wp.end();
    }

    private void end() {
        in.close();
    }

    private static boolean playAgain() {
        while (true) {
            System.out.print("Would you like to play again? (Y/N) > ");
            String response = in.nextLine();
            if (response.toUpperCase().startsWith("Y")) {
                return true;
            } else if (response.toUpperCase().startsWith("N")) {
                return false;
            }
        }
    }

    private void init() {
        //Setup Scanner
        in = new Scanner(System.in);

        //pre-load the two dictionaries
        FullDictionary.getInstance();
        AnswersDictionary.getInstance();
    }

    private void run() {
        WordleGame game = new WordleGame();
        System.out.println("Here we go!");
        while (!game.isGameOver()) {
            String guess = promptWord();
            try {
                WordleResult[] result = game.submitGuess(guess);
                System.out.println(Arrays.toString(result));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again!");
            }
        }

        if (game.hasWon) {
            System.out.println("Congratulations! You won in " + game.getNumGuesses());
        } else if (game.hasLost) {
            System.out.println("Sorry, you are out of guesses... The word was " + game.getAnswer() + " guesses");
        }
    }

    private String promptWord() {
        System.out.print("Enter a 5-letter word> ");
        return in.nextLine();
    }
}
