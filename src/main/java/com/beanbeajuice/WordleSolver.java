package com.beanbeajuice;

import com.beanbeajuice.helper.FileHelper;

import java.io.IOException;
import java.util.ArrayList;

public class WordleSolver {

    public static void main(String[] args) throws IOException {
        FileHelper wordFileReader = new FileHelper();
        ArrayList<String> words = wordFileReader.readFile(wordFileReader.getFile("words.txt"));

        ArrayList<String> fiveLetterWords = new ArrayList<>();

        words.forEach((word) -> {
            if (word.length() == 5) {
                fiveLetterWords.add(word);
            }
        });

        fiveLetterWords.forEach(System.out::println);

    }

}
