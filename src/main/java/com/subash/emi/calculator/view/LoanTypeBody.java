package com.subash.emi.calculator.view;

/**
 * LoanTypeBody
 */
public class LoanTypeBody {
    /**
     * Base currency using in the bank
     */
    public enum CurrencyEnum {
        USD, INR, EUR, SGD, GBP, CHY
    }

    private CurrencyEnum currency;
    private String description;

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
