package com.subash.emi.calculator.core;

import com.subash.emi.calculator.model.CalculatorSpecs;
import com.subash.emi.calculator.model.LoanType;
import com.subash.emi.calculator.repository.CalculatorSpecsRepository;
import com.subash.emi.calculator.repository.LoanTypeRepository;
import com.subash.emi.calculator.util.Constants;
import com.subash.emi.calculator.util.ErrorCodes;
import com.subash.emi.calculator.util.GenericLogger;
import com.subash.emi.calculator.view.CalculatorBoundaryBody;
import com.subash.emi.calculator.view.CreateCalculatorBoundaryBody;
import com.subash.emi.calculator.view.EMICalculatorResponse;
import com.subash.emi.calculator.view.GetCalculatorBoundaryBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Class helps to manage create, update and fetch calculator parameter Specs details
 *
 * @author subash s
 */
@Component
public class CalculatorParametersProcessor {

    private static final Logger logger = LogManager.getLogger(CalculatorParametersProcessor.class);

    @Autowired
    private CalculatorSpecsRepository calculatorSpecsRepository;

    @Autowired
    private LoanTypeRepository loanTypeRepository;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minInterestRate;
    private Integer maxInterestRate;
    private Integer minTermsInMonth;
    private Integer maxTermsInMonth;

    /**
     * Method helps to create calculator specs
     *
     * @param uuid
     * @param createCalculatorBoundaryBody
     * @return
     */
    public EMICalculatorResponse createCalculatorParameters(String uuid,
                                                            CreateCalculatorBoundaryBody createCalculatorBoundaryBody) {
        EMICalculatorResponse response = new EMICalculatorResponse();
        try {
            Optional<CalculatorSpecs> calculatorSpecsOptional = calculatorSpecsRepository.findById(createCalculatorBoundaryBody.getLoanType());
            if (calculatorSpecsOptional.isPresent()) {
                // returning error response if already record exits for the loan type
                response.setAdditionalInfo(ErrorCodes.EM0_CALC_01_DESC);
                response.setReturnCode(ErrorCodes.EMI_CALC_01_CODE);
            } else {
                Optional<LoanType> loanTypeOptional = loanTypeRepository.findById(createCalculatorBoundaryBody.getLoanType());
                if (!loanTypeOptional.isPresent()) {
                    // returning error response if record exist with inputted loan type
                    response.setAdditionalInfo(ErrorCodes.EM0_CALC_03_DESC);
                    response.setReturnCode(ErrorCodes.EMI_CALC_03_CODE);
                } else {
                    CalculatorSpecs calculatorSpecs = new CalculatorSpecs();
                    calculatorSpecs.setLoanType(createCalculatorBoundaryBody.getLoanType());
                    maxAmount = createCalculatorBoundaryBody.getMaxAmount();
                    minAmount = createCalculatorBoundaryBody.getMinAmount();
                    maxInterestRate = createCalculatorBoundaryBody.getMaxInterestRate().intValue();
                    minInterestRate = createCalculatorBoundaryBody.getMinInterestRate().intValue();
                    maxTermsInMonth = createCalculatorBoundaryBody.getMaxTermsInMonth().intValue();
                    minTermsInMonth = createCalculatorBoundaryBody.getMinTermsInMonth().intValue();
                    saveCalculatorSpecs(calculatorSpecs);
                    calculatorSpecsRepository.save(calculatorSpecs);
                    response.setAdditionalInfo(Constants.CREATE_RECORD_SUCCESS);
                    GenericLogger.logResponse(logger, uuid, "SUCCESS", Constants.CREATE_RECORD_SUCCESS);
                }
            }
        } catch (Exception e) {
            response.setAdditionalInfo(Constants.CREATE_RECORD_FAILURE);
            // Logger error response
            GenericLogger.logResponse(logger, uuid, "ERROR", Constants.CREATE_RECORD_FAILURE);
            logger.debug(Constants.API_PROCESSED_FAILURE + " : " + e.getMessage());
        }
        return response;
    }

    /**
     * Method helps to save calculator specs details
     *
     * @param calculatorSpecs
     */
    private void saveCalculatorSpecs(CalculatorSpecs calculatorSpecs) {
        calculatorSpecs.setMaxInterestRate(maxInterestRate);
        calculatorSpecs.setMinInterestRate(minInterestRate);
        calculatorSpecs.setMaxTermsInMonth(maxTermsInMonth);
        calculatorSpecs.setMinTermsInMonth(minTermsInMonth);
        calculatorSpecs.setMaxAmount(maxAmount);
        calculatorSpecs.setMinAmount(minAmount);
        calculatorSpecsRepository.save(calculatorSpecs);
    }

    /**
     * Method helps to update calculator specs
     *
     * @param uuid
     * @param loanTypeId
     * @param calculatorBoundaryBody
     * @return
     */
    public EMICalculatorResponse updateCalculatorParameters(String uuid, String loanTypeId,
                                                            CalculatorBoundaryBody calculatorBoundaryBody) {
        EMICalculatorResponse response = new EMICalculatorResponse();
        try {
            Optional<CalculatorSpecs> calculatorSpecsOptional = calculatorSpecsRepository.findById(loanTypeId);
            if (!calculatorSpecsOptional.isPresent()) {
                // returning error response if record is not exits for the loan type
                response.setAdditionalInfo(ErrorCodes.EM0_CALC_02_DESC);
                response.setReturnCode(ErrorCodes.EMI_CALC_02_CODE);
            } else {
                maxAmount = calculatorBoundaryBody.getMaxAmount();
                minAmount = calculatorBoundaryBody.getMinAmount();
                maxInterestRate = calculatorBoundaryBody.getMaxInterestRate().intValue();
                minInterestRate = calculatorBoundaryBody.getMinInterestRate().intValue();
                maxTermsInMonth = calculatorBoundaryBody.getMaxTermsInMonth().intValue();
                minTermsInMonth = calculatorBoundaryBody.getMinTermsInMonth().intValue();
                saveCalculatorSpecs(calculatorSpecsOptional.get());
                response.setAdditionalInfo(Constants.UPDATE_RECORD_SUCCESS);
                GenericLogger.logResponse(logger, uuid, "SUCCESS", Constants.UPDATE_RECORD_SUCCESS);
            }
        } catch (Exception e) {
            response.setAdditionalInfo(Constants.UPDATE_RECORD_FAILURE);
            // Logger error response
            GenericLogger.logResponse(logger, uuid, "ERROR", Constants.UPDATE_RECORD_FAILURE);
            logger.debug(Constants.API_PROCESSED_FAILURE + " : " + e.getMessage());
        }
        return response;
    }

    /**
     * Method helps to fetch the calculator specs details
     *
     * @param uuid
     * @param loanTypeId
     * @return
     */
    public GetCalculatorBoundaryBody getCalculatorParameters(String uuid, String loanTypeId) {
        GetCalculatorBoundaryBody response = new GetCalculatorBoundaryBody();
        try {
            Optional<CalculatorSpecs> calculatorSpecsOptional = calculatorSpecsRepository.findById(loanTypeId);
            Optional<LoanType> loanTypeOptional = loanTypeRepository.findById(loanTypeId);
            if (!calculatorSpecsOptional.isPresent() && !loanTypeOptional.isPresent()) {
                // returning error response if record is not exits for the loan type
                response.setAdditionalInfo(ErrorCodes.EM0_CALC_02_DESC);
                response.setReturnCode(ErrorCodes.EMI_CALC_02_CODE);
            } else {
                LoanType loanType = loanTypeOptional.get();
                CalculatorSpecs calculatorSpecs = calculatorSpecsOptional.get();
                response.setCurrencySymbol(loanType.getCurrencySymbol());
                response.setMaxAmount(calculatorSpecs.getMaxAmount());
                response.setMinAmount(calculatorSpecs.getMinAmount());
                response.setMaxInterestRate(new BigDecimal(calculatorSpecs.getMaxInterestRate()));
                response.setMinInterestRate(new BigDecimal(calculatorSpecs.getMinInterestRate()));
                response.setMaxTermsInMonth(new BigDecimal(calculatorSpecs.getMaxTermsInMonth()));
                response.setMinTermsInMonth(new BigDecimal(calculatorSpecs.getMinTermsInMonth()));
                GenericLogger.logResponse(logger, uuid, "SUCCESS", response);
            }
        } catch (Exception e) {
            response.setAdditionalInfo(Constants.API_PROCESSED_FAILURE);
            // Logger error response
            GenericLogger.logResponse(logger, uuid, "ERROR", Constants.API_PROCESSED_FAILURE);
            logger.debug(Constants.API_PROCESSED_FAILURE + " : " + e.getMessage());
        }
        return response;
    }
}
