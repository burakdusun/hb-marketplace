package com.bdusun.util;

import com.bdusun.command.BaseCommand;
import com.bdusun.command.CommandFactory;
import com.bdusun.model.CommandArgumentWrapper;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;

public class ScenarioFileReader {

    private ScenarioFileReader() {}

    public static File getScenarioFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.isFile()) {
            return file;
        } else {
            throw new NoSuchFileException("Wrong file path" + filePath);
        }
    }

    public static List<File> getScenarioFileList(String folderPath) throws IOException {
        File dir = new File(folderPath);
        File[] files = dir.listFiles((d, name) -> name.startsWith("SCN"));
        if (files == null) {
            throw new NoSuchFileException("Wrong folder path or no files in folder path: " + folderPath);
        }
        return Arrays.asList(files);
    }

    public static BufferedReader getBufferedReaderForFile(File file) throws IOException {
        return new BufferedReader(new FileReader(file));
    }

    public static CommandArgumentWrapper getCommandFromLine(BufferedReader bufferedReader) throws IOException {
        String line;
        if ((line = bufferedReader.readLine()) != null && !line.equals("")) {
            String[] tokens = line.split(" ");
            String commandName = tokens[0];
            System.out.println(commandName);
            BaseCommand command = CommandFactory.getCommand(commandName);
            String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
            return new CommandArgumentWrapper(command, arguments);
        }
        return null;
    }

}
