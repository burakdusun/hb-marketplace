package com.bdusun.command;

import com.bdusun.model.Product;
import com.bdusun.core.ScenarioContext;

import java.math.BigDecimal;


public class CreateProductCommand extends BaseCommand {

    @Override
    int getArgCount() {
        return 3;
    }

    @Override
    public void execute(ScenarioContext scenarioContext, String... args) {
        super.execute(scenarioContext, args);
        String productCode = args[0];
        BigDecimal price = new BigDecimal(args[1]);
        int stock = Integer.parseInt(args[2]);
        Product product = new Product(productCode, price, stock);
        scenarioContext.addProduct(product);
    }

}
