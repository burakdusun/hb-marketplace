package com.bdusun.core;

import com.bdusun.model.CommandArgumentWrapper;
import com.bdusun.util.ScenarioFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class CaseStudy {

    private CaseStudy(){}

    public static void runCaseStudyDefault() throws IOException {
        runCaseStudyForAllFilesInDirectory(System.getProperty("user.dir") + "/Scenarios");
    }

    public static void runCaseStudyForAllFilesInDirectory(String folderPath) throws IOException {
        List<File> scenarioFiles = ScenarioFileReader.getScenarioFileList(folderPath);
        for (File file : scenarioFiles) {
            runCaseStudyForFile(file);
        }
    }

    public static void runCaseStudyForFilePath(String filePath) throws IOException {
        File file = ScenarioFileReader.getScenarioFile(filePath);
        runCaseStudyForFile(file);
    }

    private static void runCaseStudyForFile(File file) throws IOException {
        System.out.println("Reading file " + file.getName() + "\n");
        TimeSingleInstance time = new TimeSingleInstance();
        ScenarioContext scenarioContext = new ScenarioContext(time);

        BufferedReader bufferedReader = ScenarioFileReader.getBufferedReaderForFile(file);
        CommandArgumentWrapper commandWrapper = ScenarioFileReader.getCommandFromLine(bufferedReader);
        while (commandWrapper != null) {
            commandWrapper.getCommand().execute(scenarioContext, commandWrapper.getArguments());
            commandWrapper = ScenarioFileReader.getCommandFromLine(bufferedReader);
        }
        System.out.println();
    }

}
