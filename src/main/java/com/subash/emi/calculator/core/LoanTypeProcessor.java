package com.subash.emi.calculator.core;

import com.subash.emi.calculator.model.CalculatorSpecs;
import com.subash.emi.calculator.model.LoanType;
import com.subash.emi.calculator.repository.CalculatorSpecsRepository;
import com.subash.emi.calculator.repository.LoanTypeRepository;
import com.subash.emi.calculator.util.Constants;
import com.subash.emi.calculator.util.ErrorCodes;
import com.subash.emi.calculator.util.GenericLogger;
import com.subash.emi.calculator.util.GenericUtil;
import com.subash.emi.calculator.view.CreateLoanTypeBody;
import com.subash.emi.calculator.view.EMICalculatorResponse;
import com.subash.emi.calculator.view.GetLoanTypeBody;
import com.subash.emi.calculator.view.LoanTypeBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Processor class helps to have methods for create, update, fetch and delete Loan Types
 *
 * @author subash s
 */
@Component
public class LoanTypeProcessor {

    private static final Logger logger = LogManager.getLogger(LoanTypeProcessor.class);
    private String currency;
    private String description;

    @Autowired
    LoanTypeRepository loanTypeRepository;

    @Autowired
    private CalculatorSpecsRepository calculatorSpecsRepository;

    /**
     * Method helps to create record in Loan type
     *
     * @param createLoanTypeBody
     * @param uuid
     * @return
     */
    public EMICalculatorResponse createLoanType(String uuid, CreateLoanTypeBody createLoanTypeBody) {
        EMICalculatorResponse response = new EMICalculatorResponse();
        try {
            Optional<LoanType> loanTypeOptional = loanTypeRepository.findById(createLoanTypeBody.getLoanType());
            if (loanTypeOptional.isPresent()) {
                // returning error response if already record exits for the loan type
                response.setAdditionalInfo(ErrorCodes.EM0_CALC_01_DESC);
                response.setReturnCode(ErrorCodes.EMI_CALC_01_CODE);
            } else {
                LoanType loanType = new LoanType();
                currency = createLoanTypeBody.getCurrency().toString();
                description = createLoanTypeBody.getDescription();
                loanType.setLoanType(createLoanTypeBody.getLoanType());
                saveLoanType(loanType);
                response.setAdditionalInfo(Constants.CREATE_RECORD_SUCCESS);
                GenericLogger.logResponse(logger, uuid, "SUCCESS", Constants.CREATE_RECORD_SUCCESS);
            }
        } catch (Exception e) {
            // Logger error response
            GenericLogger.logResponse(logger, uuid, "ERROR", Constants.CREATE_RECORD_FAILURE);
            logger.debug(Constants.API_PROCESSED_FAILURE + " : " + e.getMessage());
            response.setAdditionalInfo(Constants.CREATE_RECORD_FAILURE);
        }
        return response;
    }

    /**
     * Method helps to update record in loan type
     *
     * @param uuid
     * @param loanTypeId
     * @param loanTypeBody
     * @return
     */
    public EMICalculatorResponse updateLoanType(String uuid, String loanTypeId, LoanTypeBody loanTypeBody) {
        EMICalculatorResponse response = new EMICalculatorResponse();
        try {
            Optional<LoanType> loanTypeOptional = loanTypeRepository.findById(loanTypeId);
            if (!loanTypeOptional.isPresent()) {
                // returning error response if record is not exits for the loan type
                response.setAdditionalInfo(ErrorCodes.EM0_CALC_02_DESC);
                response.setReturnCode(ErrorCodes.EMI_CALC_02_CODE);
            } else {
                LoanType loanType = loanTypeOptional.get();
                currency = loanTypeBody.getCurrency().toString();
                description = loanTypeBody.getDescription();
                saveLoanType(loanType);
                response.setAdditionalInfo(Constants.UPDATE_RECORD_SUCCESS);
                GenericLogger.logResponse(logger, uuid, "SUCCESS", Constants.UPDATE_RECORD_SUCCESS);
            }
        } catch (Exception e) {
            // Logger error response
            GenericLogger.logResponse(logger, uuid, "ERROR", Constants.UPDATE_RECORD_FAILURE);
            logger.debug(Constants.API_PROCESSED_FAILURE + " : " + e.getMessage());
            response.setAdditionalInfo(Constants.UPDATE_RECORD_FAILURE);
        }
        return response;
    }

    /**
     * Method helps to save Loan type
     *
     * @param loanType
     */
    private void saveLoanType(LoanType loanType) {
        loanType.setCurrency(currency);
        loanType.setDescription(description);
        // get the Currency symbol for the inputted currency
        String currencySymbol = GenericUtil.getCurrencySymbol(currency);
        loanType.setCurrencySymbol(currencySymbol);
        loanTypeRepository.save(loanType);
    }

    /**
     * Method helps to fetch loan type records
     *
     * @param uuid
     * @param loanTypeId
     * @return
     */
    public GetLoanTypeBody getLoanType(String uuid, String loanTypeId) {
        GetLoanTypeBody response = new GetLoanTypeBody();
        try {
            List<CreateLoanTypeBody> loanTypeBodyList = new ArrayList<>();
            if (loanTypeId.equalsIgnoreCase("All")) {
                List<LoanType> loanTypeList = loanTypeRepository.findAll();
                for (LoanType loanType : loanTypeList) {
                    Optional<CalculatorSpecs> calculatorSpecsOptional = calculatorSpecsRepository.findById(loanType.getLoanType());
                    if (calculatorSpecsOptional.isPresent()) {
                        loanTypeBodyList.add(getLoanTypeBody(loanType));
                    }
                }
            } else {
                Optional<LoanType> loanTypeOptional = loanTypeRepository.findById(loanTypeId);
                Optional<CalculatorSpecs> calculatorSpecsOptional = calculatorSpecsRepository.findById(loanTypeId);
                if (!loanTypeOptional.isPresent() || !calculatorSpecsOptional.isPresent()) {
                    // returning error response if record is not exits for the loan type
                    response.setAdditionalInfo(ErrorCodes.EM0_CALC_02_DESC);
                    response.setReturnCode(ErrorCodes.EMI_CALC_02_CODE);
                    response.setLoanTypes(new ArrayList<>());
                } else {
                    LoanType loanType = loanTypeOptional.get();
                    loanTypeBodyList.add(getLoanTypeBody(loanType));
                }
            }
            if (response.getReturnCode() == null || response.getReturnCode().isEmpty()) {
                response.setLoanTypes(loanTypeBodyList);
            }
            GenericLogger.logResponse(logger, uuid, "SUCCESS", response);
        } catch (Exception e) {
            // Logger error response
            GenericLogger.logResponse(logger, uuid, "ERROR", Constants.API_PROCESSED_FAILURE);
            logger.debug(Constants.API_PROCESSED_FAILURE + " : " + e.getMessage());
            response.setAdditionalInfo(Constants.API_PROCESSED_FAILURE);
        }
        return response;
    }

    /**
     * Method helps to construct the get API response body
     *
     * @param loanType
     * @return
     */
    private CreateLoanTypeBody getLoanTypeBody(LoanType loanType) {
        CreateLoanTypeBody loanTypeBody = new CreateLoanTypeBody();
        loanTypeBody.setLoanType(loanType.getLoanType());
        loanTypeBody.setCurrency(LoanTypeBody.CurrencyEnum.valueOf(loanType.getCurrency()));
        loanTypeBody.setDescription(loanType.getDescription());
        return loanTypeBody;
    }

    /**
     * Method helps to delete the Loan type
     *
     * @param uuid
     * @param loanTypeId
     * @return
     */
    public EMICalculatorResponse deleteLoanType(String uuid, String loanTypeId) {
        EMICalculatorResponse response = new EMICalculatorResponse();
        try {
            Optional<LoanType> loanTypeOptional = loanTypeRepository.findById(loanTypeId);
            if (!loanTypeOptional.isPresent()) {
                // returning error response if record is not exits for the loan type
                response.setAdditionalInfo(ErrorCodes.EM0_CALC_02_DESC);
                response.setReturnCode(ErrorCodes.EMI_CALC_02_CODE);
            } else {
                Optional<CalculatorSpecs> calculatorSpecsOptional = calculatorSpecsRepository.findById(loanTypeId);
                if (calculatorSpecsOptional.isPresent()) {
                    CalculatorSpecs calculatorSpecs = calculatorSpecsOptional.get();
                    calculatorSpecsRepository.delete(calculatorSpecs);
                }
                LoanType loanType = loanTypeOptional.get();
                loanTypeRepository.delete(loanType);
                response.setAdditionalInfo(Constants.DELETE_RECORD_SUCCESS);
                GenericLogger.logResponse(logger, uuid, "SUCCESS", response);
            }
        } catch (Exception e) {
            // Logger error response
            GenericLogger.logResponse(logger, uuid, "ERROR", Constants.DELETE_RECORD_FAILURE);
            logger.debug(Constants.API_PROCESSED_FAILURE + " : " + e.getMessage());
            response.setAdditionalInfo(Constants.DELETE_RECORD_FAILURE);
        }
        return response;
    }
}
