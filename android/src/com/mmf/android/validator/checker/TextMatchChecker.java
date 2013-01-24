package com.mmf.android.validator.checker;

import android.widget.TextView;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public class TextMatchChecker extends BaseValidatorConditionChecker {

    private TextView viewToMatch;

    /**
     * Create checker and set view to match text with text in given view.
     * @param errorMessage error message.
     * @param viewToMatch view to check text.
     */
    public TextMatchChecker(String errorMessage, TextView viewToMatch) {
        super(errorMessage);
        this.viewToMatch = viewToMatch;
    }

    @Override
    public boolean checkCondition(String textToCheck) {
        return viewToMatch != null && textToCheck.equals(viewToMatch.getText().toString());
    }
}
