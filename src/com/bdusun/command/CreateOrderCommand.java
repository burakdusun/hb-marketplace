package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.model.Campaign;
import com.bdusun.model.Order;
import com.bdusun.model.Product;


public class CreateOrderCommand extends BaseCommand {

    @Override
    int getArgCount() {
        return 2;
    }

    @Override
    public void execute(ScenarioContext scenarioContext, String... args) {
        super.execute(scenarioContext, args);
        String productCode = args[0];
        int quantity = Integer.parseInt(args[1]);

        Product product = scenarioContext.getProduct(productCode);
        if (product != null && product.isStockEnough(quantity)) {

            product.decreaseStock(quantity);
            Order order = new Order(productCode, quantity);
            scenarioContext.addOrder(order);

            Campaign campaign = scenarioContext.getActiveCampaign(productCode);
            if (campaign != null) {
                campaign.updateStatisticsAfterOrder(quantity, product.getPrice(), product.getPriceMargin());
            }
        } else {
            throw new IllegalArgumentException("Product does not exist or stock is not enough");
        }
    }

}
