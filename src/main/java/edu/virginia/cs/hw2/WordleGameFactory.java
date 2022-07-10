package edu.virginia.cs.hw2;

public class WordleGameFactory {
    public WordleGame getNewWordleGame() {
        return new WordleGameImpl();
    }
}
