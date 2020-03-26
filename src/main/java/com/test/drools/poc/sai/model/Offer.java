package com.test.drools.poc.sai.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of Offer Object
 * @author Sai Kumar
 */
public class Offer {

    private ProductPackage financialPackage;
    private List<Product> products = new ArrayList();
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setFinancialPackage(ProductPackage financialPackage) {
        this.financialPackage = financialPackage;
    }

    public ProductPackage getFinancialPackage() {
        return financialPackage;
    }

    public void addSingleProduct(Product prod){
        products.add(prod);
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * Enum for Product
     */
    public enum Product {
        LOAN,
        SUPERLOAN,
        INSURANCE,
        TEST
    }

    /**
     * Enum for Product Package
     */
    public enum ProductPackage {
        GETTINGSTARTED_PACKAGE,
        CAREERFOCUSED_PACKAGE,
        ADVICEFAMILY_PACKAGE,
        EMPTYNESTER_PACKAGE,
        GOLDENYEARS_PACKAGE,
        BUSINESS_PACKAGE
    }

    @Override
    public String toString() {
        return "Offer{" +
                "financialPackage=" + financialPackage +
                ", products=" + products +
                ", discount=" + discount +
                '}';
    }
}
