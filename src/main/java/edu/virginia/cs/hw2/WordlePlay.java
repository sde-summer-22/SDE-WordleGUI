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
            String userResponse = promptForYesOrNo("Would you like to play again? (Y/N) > ");
            if(userResponse.equals("YES")) {
                return true;
            } else if (userResponse.equals("NO")) {
                return false;
            }
        }
    }

    private static String promptForYesOrNo(String prompt) {
        System.out.print(prompt);
        String response = in.nextLine();
        if (response.toUpperCase().startsWith("Y")) {
            return "YES";
        } else if (response.toUpperCase().startsWith("N")) {
            return "NO";
        } else {
            return "INVALID";
        }
    }

    private void init() {
        //Setup Scanner
        in = new Scanner(System.in);

        //preload the two dictionaries
        FullDictionary.getInstance();
        AnswersDictionary.getInstance();
    }

    private void run() {
        WordleGameFactory wgf = new WordleGameFactory();
        WordleGame game = wgf.getNewWordleGame();
        System.out.println("Here we go!");
        while (!game.isGameOver()) {
            playOneWordleRound(game);
        }
        handlePostGame(game);
    }

    private void handlePostGame(WordleGame game) {
        if (game.isWin()) {
            System.out.println("Congratulations! You won in " + game.getNumGuesses());
        } else if (game.isLost()) {
            System.out.println("Sorry, you are out of guesses... The word was " + game.getAnswer() + " guesses");
        }
    }

    private void playOneWordleRound(WordleGame game) {
        String guess = promptWord();
        try {
            WordleResult[] result = game.submitGuess(guess);
            System.out.println(Arrays.toString(result));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again!");
        }
    }

    private String promptWord() {
        System.out.print("Enter a 5-letter word> ");
        return in.nextLine();
    }
}
