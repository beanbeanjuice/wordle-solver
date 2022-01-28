package com.beanbeajuice;

import com.beanbeajuice.helper.FileHelper;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordleSolver {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FileHelper wordFileReader = new FileHelper();
        ArrayList<String> words = wordFileReader.readFile(wordFileReader.getFile("words.txt"));

        ArrayList<String> fiveLetterWords = new ArrayList<>();

        ArrayList<ArrayList<Character>> lettersNotUsed = new ArrayList<>();

        words.forEach((word) -> {
            if (word.length() == 5) {
                fiveLetterWords.add(word);
            }
        });

        ArrayList<String> availableWords = new ArrayList<>(fiveLetterWords);

        for (int i = 0; i < 5; i++) {
            lettersNotUsed.add(new ArrayList<>());
        }

        boolean wordFound = false;
        String currentInput = "*****";
        while (!wordFound) {

            ArrayList<Character> characters = new ArrayList<>();

            for (int i = 0; i < currentInput.length(); i++) {
                characters.add(currentInput.charAt(i));
            }

            ArrayList<String> tempArray = new ArrayList<>();
            availableWords.forEach((word) -> {

                if (checkWord(word, characters, lettersNotUsed)) {
                    tempArray.add(word);
                }

            });

            availableWords = new ArrayList<>(tempArray);

            System.out.println("Possible Words");
            availableWords.forEach(System.out::println);

            do {
                System.out.print("Enter Word (5 Letters Only): ");
                currentInput = scanner.next();
                System.out.print("Enter Correct (Use * For Not Correct): ");
                String correct = scanner.next();

                for (int i = 0; i < 5; i++) {
                    if (correct.charAt(i) == '*') {
                        lettersNotUsed.get(i).add(currentInput.charAt(i));
                    }
                }

                currentInput = correct;

            } while (currentInput.length() != 5);

            if (!currentInput.contains("*")) {
                wordFound = true;
            }
        }

        System.out.println("Congratulations! You cheated!");

    }

    public static boolean checkWord(@NotNull String word, @NotNull ArrayList<Character> checker, @NotNull ArrayList<ArrayList<Character>> lettersNotUsed) {
        for (int i = 0; i < 5; i++) {
            if (checker.get(i) != '*') {
                if (word.charAt(i) != checker.get(i)) {
                    return false;
                }

            }

            for (Character character : lettersNotUsed.get(i)) {
                if (word.charAt(i) == character) {
                    return false;
                }
            }
        }

        return true;
    }

}
