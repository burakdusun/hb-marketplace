package com.bdusun.command;

import com.bdusun.model.Product;
import com.bdusun.core.ScenarioContext;


public class GetProductInfoCommand extends BaseCommand {

    @Override
    int getArgCount() {
        return 1;
    }

    @Override
    public void execute(ScenarioContext scenarioContext, String... args) {
        super.execute(scenarioContext, args);
        String productCode = args[0];
        Product product = scenarioContext.getProduct(productCode);
        product.printProductMessage(true);
    }
}
