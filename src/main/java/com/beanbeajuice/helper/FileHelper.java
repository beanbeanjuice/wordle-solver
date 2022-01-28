package com.beanbeajuice.helper;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;

public class FileHelper {

    private final String path;

    public FileHelper() {
        this.path = "";
    }

    public FileHelper(@NotNull String path) {
        this.path = path;
    }

    public File getFile(@NotNull String fileName) {
        return new File(fileName);
    }

    public ArrayList<String> readFile(@NotNull File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> lineList = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lineList.add(line);
        }

        fileReader.close();
        bufferedReader.close();
        return lineList;
    }

}
