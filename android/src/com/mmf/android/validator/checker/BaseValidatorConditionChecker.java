package com.mmf.android.validator.checker;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public abstract class BaseValidatorConditionChecker {

    private String errorMessage;


    /**
     * Create checker with default error message.
     */
    public BaseValidatorConditionChecker() {
        setErrorMessage("Error");
    }

    /**
     * Create checker and set error message.
     */
    public BaseValidatorConditionChecker(String errorMessage) {
        setErrorMessage(errorMessage);
    }

    /**
     * Check whether text fulfill the condition.
     */
    public abstract boolean checkCondition(String textToCheck);

    /**
     * Set error message.
     */
    public void setErrorMessage(String message) {
        errorMessage = message;
    }

    /**
     * Get error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
