package com.subash.emi.calculator.view;

import java.math.BigDecimal;

/**
 * CalculatorBoundaryBody
 *
 * @author subash s
 */
public class CalculatorBoundaryBody {

    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal minInterestRate;
    private BigDecimal maxInterestRate;
    private BigDecimal minTermsInMonth;
    private BigDecimal maxTermsInMonth;

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

    public BigDecimal getMinInterestRate() {
        return minInterestRate;
    }

    public void setMinInterestRate(BigDecimal minInterestRate) {
        this.minInterestRate = minInterestRate;
    }

    public BigDecimal getMaxInterestRate() {
        return maxInterestRate;
    }

    public void setMaxInterestRate(BigDecimal maxInterestRate) {
        this.maxInterestRate = maxInterestRate;
    }

    public BigDecimal getMinTermsInMonth() {
        return minTermsInMonth;
    }

    public void setMinTermsInMonth(BigDecimal minTermsInMonth) {
        this.minTermsInMonth = minTermsInMonth;
    }

    public BigDecimal getMaxTermsInMonth() {
        return maxTermsInMonth;
    }

    public void setMaxTermsInMonth(BigDecimal maxTermsInMonth) {
        this.maxTermsInMonth = maxTermsInMonth;
    }
}
