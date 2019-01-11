package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.core.TimeSingleInstance;
import com.bdusun.model.Campaign;
import com.bdusun.model.Product;

import java.util.List;

public class IncreaseTimeCommand extends BaseCommand {

    @Override
    int getArgCount() {
        return 1;
    }

    @Override
    public void execute(ScenarioContext scenarioContext, String... args) {
        super.execute(scenarioContext, args);
        int elapsedTimeInHour = Integer.parseInt(args[0]);
        TimeSingleInstance time = scenarioContext.getTime();
        time.increaseTime(elapsedTimeInHour);
        List<Campaign> campaignList = scenarioContext.getActiveCampaignList();
        for (Campaign campaign : campaignList) {
            campaign.updateCampaign(elapsedTimeInHour);
            Product product = scenarioContext.getProduct(campaign.getProductCode());
            if (campaign.isActive()) {
                product.manipulatePriceByPercentage(campaign.getCurrentPriceManipulation());
            } else {
                product.resetPrice();
            }
        }
    }

}
