package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.core.TimeSingleInstance;
import com.bdusun.model.Campaign;
import com.bdusun.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class IncreaseTimeCommandTest {

    IncreaseTimeCommand command;
    ScenarioContext scenarioContext;
    TimeSingleInstance timeSingleInstance;

    @BeforeEach
    void setUp() {
        command = new IncreaseTimeCommand();
        timeSingleInstance = new TimeSingleInstance();
        scenarioContext = new ScenarioContext(timeSingleInstance);
        int i;
        for(i = 0; i < 5; i++) {
            scenarioContext.addProduct(new Product(("P" + i), new BigDecimal(10*(i+1)), 10*i));
            scenarioContext.addCampaign(new Campaign(("C" + i), ("P" + i), (i+1)*3, 5*i, 10*i));
        }
    }

    @AfterEach
    void tearDown() {
        command = null;
        timeSingleInstance = null;
        scenarioContext = null;
    }

    @Test
    void increaseTimeByOneHour() {
        command.execute(scenarioContext, "1");

        Product product = scenarioContext.getProduct("P"+0);
        assertEquals(BigDecimal.valueOf(10.0), product.getPrice());

        product = scenarioContext.getProduct("P" + 1);
        assertEquals(BigDecimal.valueOf(19.0), product.getPrice()); // -%5

        product = scenarioContext.getProduct("P" + 2);
        assertEquals(BigDecimal.valueOf(28.5), product.getPrice());

        product = scenarioContext.getProduct("P" + 3);
        assertEquals(BigDecimal.valueOf(38.0), product.getPrice());

        product = scenarioContext.getProduct("P" + 4);
        assertEquals(BigDecimal.valueOf(47.5), product.getPrice());


        Campaign campaign = scenarioContext.getCampaign("C" + 0);
        assertTrue(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 1);
        assertTrue(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 2);
        assertTrue(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 3);
        assertTrue(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 4);
        assertTrue(campaign.isActive());
    }

    @Test
    void increaseTimeByTenHours() {
        command.execute(scenarioContext, "10");
        int i;

        Campaign campaign = scenarioContext.getCampaign("C" + 0);
        assertFalse(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 1);
        assertFalse(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 2);
        assertFalse(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 3);
        assertTrue(campaign.isActive());

        campaign = scenarioContext.getCampaign("C" + 4);
        assertTrue(campaign.isActive());
    }

}