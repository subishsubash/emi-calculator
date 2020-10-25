package com.subash.emi.calculator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Entity file for Calculator Specs
 *
 * @author subash s
 */
@Document
public class CalculatorSpecs {

    @Id
    private String loanType;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minInterestRate;
    private Integer maxInterestRate;
    private Integer minTermsInMonth;
    private Integer maxTermsInMonth;

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getMinInterestRate() {
        return minInterestRate;
    }

    public void setMinInterestRate(Integer minInterestRate) {
        this.minInterestRate = minInterestRate;
    }

    public Integer getMaxInterestRate() {
        return maxInterestRate;
    }

    public void setMaxInterestRate(Integer maxInterestRate) {
        this.maxInterestRate = maxInterestRate;
    }

    public Integer getMinTermsInMonth() {
        return minTermsInMonth;
    }

    public void setMinTermsInMonth(Integer minTermsInMonth) {
        this.minTermsInMonth = minTermsInMonth;
    }

    public Integer getMaxTermsInMonth() {
        return maxTermsInMonth;
    }

    public void setMaxTermsInMonth(Integer maxTermsInMonth) {
        this.maxTermsInMonth = maxTermsInMonth;
    }
}
