package com.subash.emi.calculator.view;

/**
 * GetCalculatorBoundaryBody
 */
public class GetCalculatorBoundaryBody extends CalculatorBoundaryBody {

    private String returnCode;
    private String additionalInfo;
    private String currencySymbol;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
