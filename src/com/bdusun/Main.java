package com.bdusun;


import com.bdusun.core.CaseStudy;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please place files in " + System.getProperty("user.dir") + "/Scenarios");
        try {
            CaseStudy.runCaseStudyDefault();
            //CaseStudy.runCaseStudyForFilePath("/Users/bdusun/projects/hepsiburada-case-study/Scenarios/SCN1");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
