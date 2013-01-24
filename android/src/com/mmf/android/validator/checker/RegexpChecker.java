package com.mmf.android.validator.checker;

import java.util.regex.Pattern;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public class RegexpChecker extends BaseValidatorConditionChecker {

    private String regexp;

    /**
     * Create regexp checker that matches emails.
     * Do not support non-latin domains for now.
     */
    public static RegexpChecker createEmailRegexpChecker(String errorMessage) {
        return new RegexpChecker("\\b[\\w.%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b", errorMessage);
    }

    /**
     * Create checker that match regexp.
     */
    public RegexpChecker(String regexp, String errorMessage) {
        super(errorMessage);
        this.regexp = regexp;
    }

    /**
     * Overloaded constructor with default error message.
     * Similar to {@link RegexpChecker#RegexpChecker(String, String)}
     */
    public RegexpChecker(String regexp) {
        this.regexp = regexp;
    }


    @Override
    public boolean checkCondition(String textToCheck) {
        return Pattern.matches(regexp, textToCheck);
    }
}
