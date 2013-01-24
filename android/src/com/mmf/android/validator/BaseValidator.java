package com.mmf.android.validator;

import android.view.View;
import com.mmf.android.validator.checker.BaseValidatorConditionChecker;

import java.util.ArrayList;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public abstract class BaseValidator {

    /* List of checkers */
    private ArrayList<BaseValidatorConditionChecker> checkers = new ArrayList<BaseValidatorConditionChecker>();


     /**
     * Set view to validate their data.
     */
    public abstract void setViewToValidate(View view);
    /**
     * Get text from validated view.
     */
    public abstract String getTextToValidate();

    /**
     * Display an error message.
     * @param error - message, if no error pass null.
     */
    public abstract void displayError(String error) ;

    /**
     * Check all conditions.
     */
    public String performCheck() {
        for (BaseValidatorConditionChecker checker : checkers) {
            if (!checker.checkCondition(this.getTextToValidate())) {
                return checker.getErrorMessage();
            }
        }
        return null;
    }


    //Checkers -----------------------------------------------------------------

    /**
     * Add new checker to validator.
     */
    public void addConditionChecker(BaseValidatorConditionChecker checker) {
        checkers.add(checker);
    }

}

