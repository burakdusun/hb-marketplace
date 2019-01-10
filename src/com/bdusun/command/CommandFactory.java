package com.bdusun.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private CommandFactory() {}

    private static final Map<String, BaseCommand> commandList = new HashMap<>() {{
        put("create_product", new CreateProductCommand());
        put("get_product_info", new GetProductInfoCommand());
        put("create_order", new CreateOrderCommand());
        put("create_campaign", new CreateCampaignCommand());
        put("get_campaign_info", new GetCampaignInfoCommand());
        put("increase_time", new IncreaseTimeCommand());
    }};


    public static BaseCommand getCommand(String commandName) {
        return commandList.get(commandName);
    }

}
