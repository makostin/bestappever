package com.mmf.android.validator.checker;

import com.mmf.util.StringUtils;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public class NotEmptyChecker extends BaseValidatorConditionChecker {

    /**
     * Create checker that verify text is not null and not empty.
     */
    public NotEmptyChecker(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Overloaded constructor with default error message.
     * Similar to {@link NotEmptyChecker#NotEmptyChecker(String)}
     */
    public NotEmptyChecker() {
    }


    @Override
    public boolean checkCondition(String textToCheck) {
        return !StringUtils.isEmpty(textToCheck);
    }
}
