package com.bdusun.command;

import com.bdusun.core.ScenarioContext;
import com.bdusun.model.Campaign;

public class GetCampaignInfoCommand extends BaseCommand {

    @Override
    int getArgCount() {
        return 1;
    }

    @Override
    public void execute(ScenarioContext scenarioContext, String... args) {
        super.execute(scenarioContext, args);
        String campaignName = args[0];
        Campaign campaign = scenarioContext.getCampaign(campaignName);
        if (campaign != null) {
            campaign.printCampaignInfoMessage();
        }
    }

}
