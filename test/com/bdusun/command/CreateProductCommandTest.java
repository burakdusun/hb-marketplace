package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.core.TimeSingleInstance;
import com.bdusun.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductCommandTest {

    CreateProductCommand command;
    TimeSingleInstance timeSingleInstance;
    ScenarioContext scenarioContext;
    Product product;

    @BeforeEach
    void setUp() {
        command = new CreateProductCommand();
        timeSingleInstance = new TimeSingleInstance();
        scenarioContext = new ScenarioContext(timeSingleInstance);
    }

    @AfterEach
    void tearDown() {
        command = null;
        timeSingleInstance = null;
        scenarioContext = null;
    }

    @Test
    void createProduct() {
        String productCode = "P1";
        BigDecimal price = new BigDecimal(100);
        long stock = 1000;

        command.execute(scenarioContext, "P1", "100", "1000");
        assertEquals(productCode, scenarioContext.getProduct(productCode).getProductCode());
    }

}