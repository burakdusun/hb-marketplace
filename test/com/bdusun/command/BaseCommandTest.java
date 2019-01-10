package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.core.TimeSingleInstance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseCommandTest {

    @Test
    void execute() {
        BaseCommand baseCommand = new BaseCommand() {
            @Override
            int getArgCount() {
                return 1;
            }
        };
        assertThrows(IllegalArgumentException.class, () -> baseCommand.execute(null, ""));
        ScenarioContext scenarioContext = new ScenarioContext(new TimeSingleInstance());
        assertThrows(IllegalArgumentException.class, () -> baseCommand.execute(scenarioContext, "A", "B"));
    }
}