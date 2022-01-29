package com.beanbeajuice;

import com.beanbeajuice.helper.FileHelper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordSolver {

    private final ArrayList<String> possibleWords;
    private final ArrayList<Character> lettersNotInWord = new ArrayList<>();
    private final ArrayList<Character> lettersInWord = new ArrayList<>();

    public WordSolver(@NotNull ArrayList<String> possibleWords) {
        this.possibleWords = possibleWords;
    }

    public void startGame() {
        System.out.println("""
                If the letter is in the word, but in the incorrect place, use '/'.
                If the letter is in the correct place, type it out.
                If the letter is not in the word, use '*'.
                """);

        Scanner in = new Scanner(System.in);
        String currentInput = "";
        String correctString = "";
        do {
            printPossibleWords();
            System.out.print("Enter Word: ");
            currentInput = in.next();

            System.out.print("Enter Correct Input (/, *):" );
            correctString = in.next();

            parseWord(currentInput, correctString);
            currentInput = correctString;
        } while (currentInput.contains("*") || currentInput.contains("/"));

        System.out.println("Congratulations! You cheated!");
    }

    private void printPossibleWords() {
        for (String word : possibleWords) {
            System.out.println(word);
        }
    }

    private void parseWord(@NotNull String input, @NotNull String correctString) {
        ArrayList<String> wordsToRemove = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            if (correctString.charAt(i) == '*') {
                char character = input.charAt(i);
                if (!lettersInWord.contains(character)) {
                    lettersNotInWord.add(input.charAt(i));
                } else {
                    for (String possibleWord : possibleWords) {
                        if (possibleWord.charAt(i) == character) {
                            wordsToRemove.add(possibleWord);
                        }
                    }
                }
                continue;
            }

            if (correctString.charAt(i) == '/') {
                lettersInWord.add(input.charAt(i));
                for (String possibleWord : possibleWords) {
                    if (possibleWord.charAt(i) == input.charAt(i)) {
                        wordsToRemove.add(possibleWord);
                    }
                }
                continue;
            }

            for (String possibleWord : possibleWords) {
                if (possibleWord.charAt(i) != input.charAt(i)) {
                    wordsToRemove.add(possibleWord);
                }
            }
        }

        for (String possibleWord : possibleWords) {
            boolean removeWord = false;
            for (Character lettersNotInWord : lettersNotInWord) {
                if (possibleWord.contains(lettersNotInWord.toString())) {
                    removeWord = true;
                    break;
                }
            }

            for (Character lettersInWord : lettersInWord) {
                if (!possibleWord.contains(lettersInWord.toString())) {
                    removeWord = true;
                    break;
                }
            }

            if (removeWord) {
                wordsToRemove.add(possibleWord);
            }
        }

        for (String wordToRemove : wordsToRemove) {
            possibleWords.remove(wordToRemove);
        }
    }

    public static void main(String[] args) throws IOException {
        FileHelper wordFileReader = new FileHelper();
        ArrayList<String> words = wordFileReader.readFile(wordFileReader.getFile("words.txt"));
        ArrayList<String> fiveLetterWords = new ArrayList<>();

        words.forEach((word) -> {
            if (word.length() == 5) {
                fiveLetterWords.add(word);
            }
        });

        WordSolver wordSolver = new WordSolver(fiveLetterWords);
        wordSolver.startGame();
    }

}
