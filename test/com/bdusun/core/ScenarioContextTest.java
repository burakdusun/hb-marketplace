package com.bdusun.core;

import com.bdusun.model.Campaign;
import com.bdusun.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioContextTest {


    TimeSingleInstance timeSingleInstance;
    ScenarioContext scenarioContext;

    Product product;
    String productCode = "P1";
    BigDecimal price = new BigDecimal(100);
    long stock = 1000;

    Campaign campaign;
    String campaignName = "C1";
    int duration = 10;
    double priceManipulationLimit = 20;
    int targetSalesCount = 100;


    @BeforeEach
    void setUp() {
        timeSingleInstance = new TimeSingleInstance();
        scenarioContext = new ScenarioContext(timeSingleInstance);
        product = new Product(productCode, price, stock);
    }

    @AfterEach
    void tearDown() {
        timeSingleInstance = null;
        scenarioContext = null;
        product = null;
    }

    @Test
    void getProduct() {
        scenarioContext.addProduct(product);
        Product product2 = scenarioContext.getProduct(productCode);
        assertEquals(product2, product);
    }

    @Test
    void getProductWrongCode() {
        scenarioContext.addProduct(product);
        Product product2 = scenarioContext.getProduct("P2");
        assertNotEquals(product2, product);
    }

    @Test
    void getCampaign() {
        campaign = new Campaign(campaignName, productCode, duration, priceManipulationLimit, targetSalesCount);
        scenarioContext.addCampaign(campaign);
        assertEquals(scenarioContext.getCampaign(campaignName), campaign);
    }

    @Test
    void canCampaignBeCreated() {
        campaign = new Campaign(campaignName, productCode, duration, priceManipulationLimit, targetSalesCount);
        scenarioContext.addCampaign(campaign);
        assertFalse(scenarioContext.canCampaignBeCreated("C1", productCode));
        assertFalse(scenarioContext.canCampaignBeCreated("C2", productCode));
        campaign.updateCampaign(100); // set campaign inactive
        assertTrue(scenarioContext.canCampaignBeCreated("C2", productCode));

        String productCode2 = "P2";
        Product product2 = new Product(productCode2, price, stock);
        scenarioContext.addProduct(product2);
        assertFalse(scenarioContext.canCampaignBeCreated("C1", productCode2));
        assertTrue(scenarioContext.canCampaignBeCreated("C2", productCode2));
    }

    @Test
    void getActiveCampaign() {
        campaign = new Campaign(campaignName, productCode, duration, priceManipulationLimit, targetSalesCount);
        scenarioContext.addCampaign(campaign);
        assertEquals(scenarioContext.getActiveCampaign(productCode), campaign);
        campaign.updateCampaign(100);
        assertNull(scenarioContext.getActiveCampaign(productCode));
    }

    @Test
    void getActiveCampaignList() {
        campaign = new Campaign(campaignName, productCode, duration, priceManipulationLimit, targetSalesCount);
        scenarioContext.addCampaign(campaign);
        Campaign campaign2 = new Campaign("C2", (productCode+"X"), duration, priceManipulationLimit, targetSalesCount);
        scenarioContext.addCampaign(campaign2);
        List<Campaign> campaignList = new ArrayList<>();
        campaignList.add(campaign);
        campaignList.add(campaign2);
        assertEquals(scenarioContext.getActiveCampaignList(), campaignList);

        campaign2.updateCampaign(100);
        campaignList.remove(campaign2);
        assertEquals(scenarioContext.getActiveCampaignList(), campaignList);
    }

}