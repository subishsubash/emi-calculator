package com.subash.emi.calculator.controller;

import com.subash.emi.calculator.core.CalculatorParametersProcessor;
import com.subash.emi.calculator.util.Constants;
import com.subash.emi.calculator.util.GenericLogger;
import com.subash.emi.calculator.view.CalculatorBoundaryBody;
import com.subash.emi.calculator.view.CreateCalculatorBoundaryBody;
import com.subash.emi.calculator.view.EMICalculatorResponse;
import com.subash.emi.calculator.view.GetCalculatorBoundaryBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Controller class for Calculator Boundary API
 *
 * @author subash s
 */
@Controller()
public class CalculatorParameterController {

    private static final Logger logger = LogManager.getLogger(CalculatorParameterController.class);

    @Autowired
    private CalculatorParametersProcessor calculatorParametersProcessor;

    /**
     * Entry point for createCalculatorBoundary API
     *
     * @param createCalculatorBoundaryBody
     * @return
     */
    @RequestMapping(value = "/reference/calculator/settings", method = RequestMethod.POST)
    @ResponseBody
    public EMICalculatorResponse createCalcParameter(@RequestBody CreateCalculatorBoundaryBody createCalculatorBoundaryBody) {
        String uuid = GenericLogger.getUUID();
        GenericLogger.logRequest(logger, uuid, Constants.CREATE_CALCULATOR_DETAILS, "POST", createCalculatorBoundaryBody);
        EMICalculatorResponse response = calculatorParametersProcessor.createCalculatorParameters(uuid, createCalculatorBoundaryBody);
        return response;
    }

    /**
     * Entry point for updateCalculatorBoundary API
     *
     * @param loanTypeId
     * @param calculatorBoundaryBody
     * @return
     */
    @RequestMapping(value = "/reference/calculator/settings/{loanType}", method = RequestMethod.PUT)
    @ResponseBody
    public EMICalculatorResponse updateCalcParameter(@PathVariable("loanType") String loanTypeId,
                                                     @RequestBody CalculatorBoundaryBody calculatorBoundaryBody) {
        String uuid = GenericLogger.getUUID();
        GenericLogger.logRequest(logger, uuid, Constants.UPDATE_CALCULATOR_DETAILS, "PUT", calculatorBoundaryBody);
        EMICalculatorResponse response = calculatorParametersProcessor.updateCalculatorParameters(uuid, loanTypeId, calculatorBoundaryBody);
        return response;
    }

    /**
     * Entry point for getCalculatorBoundary API
     *
     * @param loanTypeId
     * @return
     */
    @RequestMapping(value = "/reference/calculator/settings/{loanType}", method = RequestMethod.GET)
    @ResponseBody
    public GetCalculatorBoundaryBody getCalcParameter(@PathVariable("loanType") String loanTypeId, HttpServletResponse httpServletResponse) {
        String uuid = GenericLogger.getUUID();
        GenericLogger.logRequest(logger, uuid, Constants.GET_CALCULATOR_DETAILS, "GET", loanTypeId);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        GetCalculatorBoundaryBody response = calculatorParametersProcessor.getCalculatorParameters(uuid, loanTypeId);
        return response;
    }
}
