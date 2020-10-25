package com.subash.emi.calculator.view;

import java.util.List;

/**
 * GetLoanTypeBody
 *
 * @author subash s
 */
public class GetLoanTypeBody {

    private String returnCode;
    private String additionalInfo;

    private List<CreateLoanTypeBody> loanTypes;

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

    public List<CreateLoanTypeBody> getLoanTypes() {
        return loanTypes;
    }

    public void setLoanTypes(List<CreateLoanTypeBody> loanTypes) {
        this.loanTypes = loanTypes;
    }
}
