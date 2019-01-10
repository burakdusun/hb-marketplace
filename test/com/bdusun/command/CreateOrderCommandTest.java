package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.core.TimeSingleInstance;
import com.bdusun.model.Campaign;
import com.bdusun.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderCommandTest {

    CreateOrderCommand command;
    TimeSingleInstance timeSingleInstance;
    ScenarioContext scenarioContext;
    Product product;
    Campaign campaign;

    @BeforeEach
    void setUp() {
        command = new CreateOrderCommand();
        timeSingleInstance = new TimeSingleInstance();
        scenarioContext = new ScenarioContext(timeSingleInstance);
    }

    @AfterEach
    void tearDown() {
        command = null;
        product = null;
        timeSingleInstance = null;
        scenarioContext = null;
        campaign = null;
    }

    @Test
    void createOrder() {
        String productCode = "P1";
        long stock = 100;
        long quantity = 10;
        product = new Product(productCode, new BigDecimal(100), stock);
        scenarioContext.addProduct(product);
        command.execute(scenarioContext, productCode, String.valueOf(quantity));
        assertEquals(product.getStock(), stock - quantity);
    }

    @Test
    void createOrderNotEnoughStock() {
        String productCode = "P1";
        long stock = 9;
        long quantity = 10;
        product = new Product(productCode, new BigDecimal(100), stock);
        scenarioContext.addProduct(product);
        assertThrows(IllegalArgumentException.class, () -> command.execute(scenarioContext, productCode, String.valueOf(quantity)));
    }

    @Test
    void createOrderForProductWithCampaign() {
        String productCode = "P1";
        long stock = 100;
        long quantity = 10;
        product = new Product(productCode, new BigDecimal(100), stock);
        scenarioContext.addProduct(product);

        Campaign campaign = new Campaign("C1", productCode, 10, 20.0, 100);
        scenarioContext.addCampaign(campaign);
        command.execute(scenarioContext, productCode, String.valueOf(quantity));
        assertEquals(quantity, campaign.getCurrentSalesCount());
        assertEquals((100-2.5)*10, campaign.getTurnover());

    }

}