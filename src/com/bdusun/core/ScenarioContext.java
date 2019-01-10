package com.bdusun.core;


import com.bdusun.model.Campaign;
import com.bdusun.model.Order;
import com.bdusun.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScenarioContext {

    private Map<String, Product> productMap;
    private List<Order> orderList; // actually kind of pointless right now
    private List<Campaign> campaignList;

    private TimeSingleInstance time;


    public ScenarioContext(TimeSingleInstance time) {
        productMap = new HashMap<>();
        orderList = new ArrayList<>();
        campaignList = new ArrayList<>();
        this.time = time;
    }


    public void addProduct(Product product) {
        if(product != null) {
            productMap.put(product.getProductCode(), product);
        }
    }

    public Product getProduct(String productCode) {
        return productMap.get(productCode);
    }

    public void addCampaign(Campaign campaign) {
        if(campaign != null) {
            campaignList.add(campaign);
        }
    }

    public Campaign getCampaign(String campaignName) {
        if (campaignName != null) {
            for (Campaign campaign : campaignList) {
                if (campaign.getName().equals(campaignName)) {
                    return campaign;
                }
            }
        }
        return null;
    }

    public boolean canCampaignBeCreated(String campaignName, String productCode) {
        if (campaignName != null && productCode != null) {
            for (Campaign campaign : campaignList) {
                if ((campaign.isForProduct(productCode) &&
                        campaign.isActive()) || campaign.getName().equals(campaignName))
                    return false;
            }
            return true;
        }
        return false;
    }

    public Campaign getActiveCampaign(String productCode) {
        if (productCode != null) {
            for (Campaign campaign : campaignList) {
                if (campaign.getProductCode().equals(productCode) &&
                        campaign.isActive())
                    return campaign;
            }
        }
        return null;
    }

    public List<Campaign> getActiveCampaignList() {
        List<Campaign> activeCampaignList = new ArrayList<>();
        for (Campaign campaign : campaignList) {
            if (campaign.isActive()) {
                activeCampaignList.add(campaign);
            }
        }
        return activeCampaignList;
    }

    public void addOrder(Order order) {
        if (order != null) {
            orderList.add(order);
        }
    }

    public TimeSingleInstance getTime() {return time;}

}
