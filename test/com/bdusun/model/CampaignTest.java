package com.bdusun.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CampaignTest {

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
        product = new Product(productCode, price, stock);
        campaign = new Campaign(campaignName, productCode, duration, priceManipulationLimit, targetSalesCount);
    }

    @AfterEach
    void tearDown() {
        product = null;
        campaign = null;
    }

    @Test
    void updateStatisticsAfterOrder() {
        long quantity = 10;
        BigDecimal price = new BigDecimal(90);
        BigDecimal priceMargin = new BigDecimal(10);

        campaign.updateStatisticsAfterOrder(quantity, price, priceMargin);
        assertEquals(campaign.getCurrentSalesCount(), 10);
        assertEquals(campaign.getAverageItemPrice(), new BigDecimal(90));
        assertEquals(campaign.getGrossYield(), new BigDecimal(900));
        assertEquals(campaign.getTurnover(), new BigDecimal(100));

        quantity = 30;
        price = new BigDecimal(60);
        priceMargin = new BigDecimal(40);
        campaign.updateStatisticsAfterOrder(quantity, price, priceMargin);
        assertEquals(campaign.getCurrentSalesCount(), 40);
        assertEquals(campaign.getAverageItemPrice(), new BigDecimal(90));
        assertEquals(campaign.getGrossYield(), new BigDecimal(2700));
        assertEquals(campaign.getTurnover(), new BigDecimal(1300));

    }

    @Test
    void updateCampaign() {
        campaign = new Campaign(campaignName, productCode, duration, priceManipulationLimit, targetSalesCount);
        assertEquals(campaign.getCurrentPriceManipulation(), 2.5);
        assertTrue(campaign.isActive());
        campaign.updateCampaign(1);
        assertEquals(campaign.getCurrentPriceManipulation(), 5.0);
        assertTrue(campaign.isActive());
        campaign.updateCampaign(7);
        assertEquals(campaign.getCurrentPriceManipulation(), 20);
        assertTrue(campaign.isActive());
        campaign.updateCampaign(2);
        assertEquals(campaign.getCurrentPriceManipulation(), 20);
        assertFalse(campaign.isActive());
    }

    @Test
    void campaignIsStillActiveAfterElapsedTime() {
        assertTrue(campaign.campaignIsStillActiveAfterElapsedTime(duration - 1));
        assertFalse(campaign.campaignIsStillActiveAfterElapsedTime(duration));
        assertFalse(campaign.campaignIsStillActiveAfterElapsedTime(duration + 1));
    }

    @Test
    void isPriceManipulationLimitAcceptable() {
        assertTrue(campaign.isPriceManipulationLimitAcceptable(50));
        assertFalse(campaign.isPriceManipulationLimitAcceptable(0));
        assertFalse(campaign.isPriceManipulationLimitAcceptable(100));
    }
}