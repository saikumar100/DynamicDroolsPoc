package com.test.drools.poc.sai.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of Customer Object
 * @author  Sai Kumar
 */
public class Customer {
    private CustomerLifeStage lifestage;
    private CustomerAssets assets;
    private List<CustomerNeed> customerNeeds = new ArrayList<>();

    public Customer() {
        super();   
    }

    public void setLifeStage(CustomerLifeStage lifestage) {

        this.lifestage = lifestage;
    }

    public CustomerLifeStage getLifeStage() {

        return lifestage;
    }

    public void addNeed(CustomerNeed need) {

        customerNeeds.add(need);
    }

    public List<CustomerNeed> getNeeds() {
        return customerNeeds;
    }

    public void setAssets(CustomerAssets assets) {
        this.assets = assets;
    }

    public CustomerAssets getAssets() {
        return assets;
    }

    /**
     * Enum for CustomerNeed
     */
    public enum CustomerNeed {
        SAVINGACCOUNT,
        LIFEINSURANCE,
        DIGITALBANKING,
        STUDENTLOAN,
        MORTAGE
    }

    /**
     * Enum for CustomerLifeStage
     */
    public enum CustomerLifeStage {
        GETTINGSTARTED,
        CAREERFOCUSED,
        ADVICEFAMILY,
        EMPTYNESTER,
        GOLDENYEARS,
        BUSINESS
    }

    /**
     * Enum for CustomerAssets
     */
    public enum CustomerAssets {
        TO50K,
        FROM50KTO150K,
        FROM150KTO300K,
        OVER300K
    }

}
