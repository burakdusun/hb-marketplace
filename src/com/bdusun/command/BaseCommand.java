package com.bdusun.command;


import com.bdusun.core.ScenarioContext;

public abstract class BaseCommand {


    abstract int getArgCount();

    public void execute(ScenarioContext scenarioContext, String... args) {
        if (args.length != getArgCount() || scenarioContext == null) {
            throw new IllegalArgumentException();
        }
    }

}
