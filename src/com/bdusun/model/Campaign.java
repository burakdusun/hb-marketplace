package com.bdusun.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Campaign {

    private String name;
    private String productCode;
    private int duration;
    private double priceManipulationLimit;
    private long targetSalesCount;

    private long currentSalesCount;
    private double currentPriceManipulation;
    private BigDecimal grossYield;
    private BigDecimal turnover;
    private BigDecimal averageItemPrice;
    private boolean isActive;
    private final int initialDuration;

    private static final double INITIAL_PRICE_MANIPULATION = 2.5;


    public Campaign(String name, String productCode, int duration, double priceManipulationLimit, long targetSalesCount) {
        this.name = name;
        this.productCode = productCode;
        this.duration = duration;
        this.priceManipulationLimit = priceManipulationLimit;
        this.targetSalesCount = targetSalesCount;

        currentSalesCount = 0;
        grossYield = new BigDecimal(0);
        turnover = new BigDecimal(0);
        averageItemPrice = new BigDecimal(0);
        isActive = true;
        initialDuration = duration;

        if (INITIAL_PRICE_MANIPULATION > priceManipulationLimit) {
            currentPriceManipulation = priceManipulationLimit;
        } else {
            currentPriceManipulation = INITIAL_PRICE_MANIPULATION;
        }
        currentPriceManipulation = calculatePriceManipulationPercentage();

        printCampainCreatedMessage();
    }


    public void updateStatisticsAfterOrder(long quantity, BigDecimal price, BigDecimal priceMargin) {
        if (quantity > 0) {
            currentSalesCount += quantity;
            grossYield = grossYield.add(price.multiply(BigDecimal.valueOf(quantity)));
            turnover = turnover.add(priceMargin.multiply(BigDecimal.valueOf(quantity)));
            averageItemPrice = grossYield.divide(BigDecimal.valueOf(quantity), RoundingMode.UP);
        }
    }

    public void updateCampaign(int timeElapsed) {
        if (campaignIsStillActiveAfterElapsedTime(timeElapsed)) {
            duration -= timeElapsed;
            currentPriceManipulation = calculatePriceManipulationPercentage();
        } else {
            duration = 0;
            isActive = false;
        }
    }

    public boolean isForProduct(String productCode) {
        return this.productCode.equals(productCode);
    }

    private double calculatePriceManipulationPercentage() {
        double currentPercentage = currentPriceManipulation;
        double percentageMargin = 2.5 * (initialDuration - duration + 1);
        if (currentSalesCount < targetSalesCount) {
            if (isManipulationPercentageWithinLimit(percentageMargin)) {
                currentPercentage = percentageMargin;
            }
        }
        return currentPercentage;
    }

    private boolean isManipulationPercentageWithinLimit(double percentage) {
        return percentage <= priceManipulationLimit;
    }

    private String getStatusName() {
        if (isActive) {
            return "Active";
        } else {
            return "Ended";
        }
    }

    private String getAverageItemPriceString() {
        if (averageItemPrice.equals(BigDecimal.ZERO)) {
            return "-";
        } else {
            return averageItemPrice.toString();
        }
    }


    private void printCampaignInfoMessage() {
        System.out.println("Campaign info; Status " + getStatusName() + ", Target Sales " + targetSalesCount + ", Total Sales " + currentSalesCount + ", Turnover " + turnover + ", AverageItemPrice " + getAverageItemPriceString());
    }

    public void printCampainCreatedMessage() {
        System.out.println("Campaign created;" + " name " + name + ", product " + getProductCode() + ", duration " + duration + ", limit " + priceManipulationLimit + ", Target Sales Count " + targetSalesCount);
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public String getProductCode() {
        return productCode;
    }

    public boolean campaignIsStillActiveAfterElapsedTime(int duration) {
        return this.duration - duration > 0;
    }

    public boolean isPriceManipulationLimitAcceptable(double priceManipulationLimit) {
        return (priceManipulationLimit > 0 && priceManipulationLimit < 100);
    }

    public void setPriceManipulationLimit(double priceManipulationLimit) {
        if (isPriceManipulationLimitAcceptable(priceManipulationLimit)) {
            this.priceManipulationLimit = priceManipulationLimit;
        }
    }

    public void setTargetSalesCount(long targetSalesCount) {
        if (targetSalesCount > 0) {
            this.targetSalesCount = targetSalesCount;
        } else {
            throw new IllegalArgumentException("Target sales count must be greater than 0");
        }
    }

    public double getCurrentPriceManipulation() {
        return currentPriceManipulation;
    }

    public int getDuration() {
        return duration;
    }

    public double getPriceManipulationLimit() {
        return priceManipulationLimit;
    }

    public long getTargetSalesCount() {
        return targetSalesCount;
    }

    public long getCurrentSalesCount() {
        return currentSalesCount;
    }

    public BigDecimal getGrossYield() {
        return grossYield;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public BigDecimal getAverageItemPrice() {
        return averageItemPrice;
    }

    public int getInitialDuration() {
        return initialDuration;
    }
}
