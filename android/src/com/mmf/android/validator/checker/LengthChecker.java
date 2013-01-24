package com.mmf.android.validator.checker;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public class LengthChecker extends BaseValidatorConditionChecker {

    private int minLength;
    private int maxLength;
    /**
     * Create checker that match text length.
     * Bounds are inclusive, so text can have length equals to minLength or maxLength.
     * @param minLength - minimal length of text (set negative value to have only max length check).
     * @param maxLength - maximal length of text.
     */
    public LengthChecker(int minLength, int maxLength, String errorMessage) {
        super(errorMessage);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    /**
     * Overloaded constructor with default error message.
     * Similar to {@link LengthChecker#LengthChecker(int, int, String)}
     */
    public LengthChecker(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }


    @Override
    public boolean checkCondition(String textToCheck) {
        int length = textToCheck.length();
        return length >= minLength && length <= maxLength;
    }
}
