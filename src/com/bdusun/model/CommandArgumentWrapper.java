package com.bdusun.model;

import com.bdusun.command.BaseCommand;

public class CommandArgumentWrapper {

    private BaseCommand baseCommand;
    private String[] arguments;

    public CommandArgumentWrapper(BaseCommand baseCommand, String[] arguments) {
        this.baseCommand = baseCommand;
        this.arguments = arguments;
    }

    public BaseCommand getCommand() {
        return baseCommand;
    }

    public String[] getArguments() {
        return arguments;
    }
}
