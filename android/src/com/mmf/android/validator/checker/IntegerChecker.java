package com.mmf.android.validator.checker;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public class IntegerChecker extends BaseValidatorConditionChecker {

    private int minValue;
    private int maxValue;

    /**
     * Create checker that convert text to int and match bounds.
     * Bounds are inclusive.
     * If text is not a number validator will fails.
     */
    public IntegerChecker(int minValue, int maxValue, String errorMessage) {
        super(errorMessage);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Overloaded constructor with default error message.
     * Similar to {@link IntegerChecker#IntegerChecker(int, int, String)}
     */
    public IntegerChecker(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }


    @Override
    public boolean checkCondition(String textToCheck) {
        try {
            int value = Integer.parseInt(textToCheck);
            return value >= minValue && value <= maxValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
