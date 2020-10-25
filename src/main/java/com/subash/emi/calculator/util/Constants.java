package com.subash.emi.calculator.util;

/**
 * Generic class to keep all the constant values
 * @author subash s
 */
public class Constants {

    // Constants for logger
    public final static String LOG_APP_NAME = "EMI Calculator";
    public final static String LOG_OPERATION_ID = "[OPERATION ID] : ";
    public final static String LOG_METHOD = "[HTTP METHOD] : ";
    public final static String LOG_REQUEST = "[REQUEST BODY] : ";
    public final static String LOG_RESPONSE = "[RESPONSE BODY] : ";
    public final static String LOG_FAILURE_MSG = "[FAILED TO LOG] : ";
    public final static String LOG_UUID = "[UUID] : ";
    public final static String LOG_STATUS = "[STATUS] : ";
    public final static String LOG_APP = "[APPLICATION] : ";

    // Operation Id
    public final static String CREATE_LOAN_TYPE_ID = "createLoanType";
    public final static String UPDATE_LOAN_TYPE_ID = "updateLoanType";
    public final static String GET_LOAN_TYPE_ID = "getLoanType";
    public final static String DELETE_LOAN_TYPE_ID = "deleteLoanType";
    public final static String CREATE_CALCULATOR_DETAILS = "createCalculatorBoundary";
    public final static String UPDATE_CALCULATOR_DETAILS = "updateCalculatorBoundary";
    public final static String GET_CALCULATOR_DETAILS = "getCalculatorBoundary";

    // API response
    public static final String CREATE_RECORD_SUCCESS = "Record created successfully";
    public static final String UPDATE_RECORD_SUCCESS = "Record updated successfully";
    public static final String DELETE_RECORD_SUCCESS = "Record deleted successfully";
    public static final String CREATE_RECORD_FAILURE = "Failed to create record";
    public static final String UPDATE_RECORD_FAILURE = "Failed to update record";
    public static final String DELETE_RECORD_FAILURE = "Failed to delete record";
    public final static String API_PROCESSED_FAILURE = "Error while processing the API";
}
