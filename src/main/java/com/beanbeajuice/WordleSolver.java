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

        words.forEach((word) -> {
            if (word.length() == 5) {
                fiveLetterWords.add(word);
            }
        });

        ArrayList<String> availableWords = new ArrayList<>(fiveLetterWords);

        boolean wordFound = false;
        String currentInput = "*****";
        while (!wordFound) {

            ArrayList<Character> characters = new ArrayList<>();

            for (int i = 0; i < currentInput.length(); i++) {
                characters.add(currentInput.charAt(i));
            }

            ArrayList<String> tempArray = new ArrayList<>();
            availableWords.forEach((word) -> {

                if (checkWord(word, characters)) {
                    tempArray.add(word);
                }

            });

            availableWords = new ArrayList<>(tempArray);

            System.out.println("Possible Words");
            availableWords.forEach(System.out::println);

            do {
                System.out.print("Enter Word (5 Letters Only): ");
                currentInput = scanner.next();
            } while (currentInput.length() != 5);
        }

    }

    public static boolean checkWord(@NotNull String word, @NotNull ArrayList<Character> checker) {
        for (int i = 0; i < 5; i++) {
            if (checker.get(i) != '*') {
                if (word.charAt(i) != checker.get(i)) {
                    return false;
                }
            }
        }

        return true;
    }

}
