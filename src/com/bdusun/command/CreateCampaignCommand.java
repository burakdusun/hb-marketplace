package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.model.Campaign;
import com.bdusun.model.Product;

public class CreateCampaignCommand extends BaseCommand {

    @Override
    int getArgCount() {
        return 5;
    }

    @Override
    public void execute(ScenarioContext scenarioContext, String... args) {
        super.execute(scenarioContext, args);

        String name = args[0];
        String productCode = args[1];
        int duration = Integer.parseInt(args[2]);
        double priceManipulationLimit = Double.parseDouble(args[3]);
        int targetSalesCount = Integer.parseInt(args[4]);

        Product product = scenarioContext.getProduct(productCode);
        if (product != null && scenarioContext.canCampaignBeCreated(name, productCode)) {
            Campaign campaign = new Campaign(name, productCode, duration, priceManipulationLimit, targetSalesCount);
            scenarioContext.addCampaign(campaign);
            product.manipulatePriceByPercentage(campaign.getCurrentPriceManipulation());
        }
    }
}
