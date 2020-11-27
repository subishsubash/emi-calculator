package com.subash.emi.calculator.controller;

import com.subash.emi.calculator.core.LoanTypeProcessor;
import com.subash.emi.calculator.util.Constants;
import com.subash.emi.calculator.util.GenericLogger;
import com.subash.emi.calculator.view.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller class for loan type APIs
 *
 * @author subash s
 */
@Controller()
public class LoanTypeController {

    private static final Logger logger = LogManager.getLogger(LoanTypeController.class);

    @Autowired
    private LoanTypeProcessor loanTypeProcessor;

    /**
     * Request mapping for create loan type API
     *
     * @param createLoanTypeBody
     * @return
     */
    @RequestMapping(value = "/system/loan", method = RequestMethod.POST)
    @ResponseBody
    public EMICalculatorResponse createLoanType(@RequestBody CreateLoanTypeBody createLoanTypeBody) {
        // Log Request
        String UUID = GenericLogger.getUUID();
        GenericLogger.logRequest(logger, UUID, Constants.CREATE_LOAN_TYPE_ID, "POST", createLoanTypeBody);
        EMICalculatorResponse response = loanTypeProcessor.createLoanType(UUID, createLoanTypeBody);
        return response;
    }

    /**
     * Request mapping for update loan type API
     *
     * @param loanTypeId
     * @return
     */
    @RequestMapping(value = "/system/loan/{loanType}", method = RequestMethod.PUT)
    @ResponseBody
    public EMICalculatorResponse updateLoanType(@PathVariable("loanType") String loanTypeId, @RequestBody LoanTypeBody loanTypeBody) {
        // Log Request
        String UUID = GenericLogger.getUUID();
        GenericLogger.logRequest(logger, UUID, Constants.UPDATE_LOAN_TYPE_ID, "PUT", loanTypeBody);
        EMICalculatorResponse response = loanTypeProcessor.updateLoanType(UUID, loanTypeId, loanTypeBody);
        return response;
    }

    /**
     * Request mapping for fetch loan type API
     * If LoanType is ALL - System will return all the loan types
     *
     * @param loanTypeId
     * @return
     */
    @RequestMapping(value = "/system/loan/{loanType}", method = RequestMethod.GET)
    @ResponseBody
    public GetLoanTypeBody getLoanType(@PathVariable("loanType") String loanTypeId, HttpServletResponse httpServletResponse) {
        // Log Request
        String UUID = GenericLogger.getUUID();
        GenericLogger.logRequest(logger, UUID, Constants.GET_LOAN_TYPE_ID, "GET", loanTypeId);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        GetLoanTypeBody response = loanTypeProcessor.getLoanType(UUID, loanTypeId);
        return response;
    }

    /**
     * Request mapping for delete loan type API
     *
     * @param loanTypeId
     * @return
     */
    @RequestMapping(value = "/system/loan/{loanType}", method = RequestMethod.DELETE)
    @ResponseBody
    public EMICalculatorResponse deleteLoanType(@PathVariable("loanType") String loanTypeId) {
        // Log Request
        String UUID = GenericLogger.getUUID();
        GenericLogger.logRequest(logger, UUID, Constants.DELETE_LOAN_TYPE_ID, "DELETE", loanTypeId);
        EMICalculatorResponse response = loanTypeProcessor.deleteLoanType(UUID, loanTypeId);
        return response;
    }
}
