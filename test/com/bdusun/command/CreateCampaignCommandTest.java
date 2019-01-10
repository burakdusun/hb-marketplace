package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.core.TimeSingleInstance;
import com.bdusun.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateCampaignCommandTest {

    CreateCampaignCommand command;
    TimeSingleInstance timeSingleInstance;
    ScenarioContext scenarioContext;
    Product product;

    String productCode = "P1";
    BigDecimal price = new BigDecimal(100);
    long stock = 100;

    String campaignName = "C1";


    @BeforeEach
    void setUp() {
        command = new CreateCampaignCommand();
        timeSingleInstance = new TimeSingleInstance();
        scenarioContext = new ScenarioContext(timeSingleInstance);

        product = new Product(productCode, price, stock);
        scenarioContext.addProduct(product);
    }

    @AfterEach
    void tearDown() {
        command = null;
        product = null;
        timeSingleInstance = null;
        scenarioContext = null;
    }

    @Test
    void createCampaign() {
        command.execute(scenarioContext, campaignName, productCode, "10", "20", "100");
        assertEquals(product.getPrice(), BigDecimal.valueOf(97.5));
    }
}