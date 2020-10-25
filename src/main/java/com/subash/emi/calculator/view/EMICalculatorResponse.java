package com.subash.emi.calculator.view;

/**
 * EMICalculatorResponse
 *
 * @author subash s
 */
public class EMICalculatorResponse {

    private String returnCode;
    private String additionalInfo;

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
}
