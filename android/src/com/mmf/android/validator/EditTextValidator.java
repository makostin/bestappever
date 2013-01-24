package com.mmf.android.validator;

import android.view.View;
import android.widget.EditText;

/**
 * @author svetlana.voyteh
 * @date: 3/27/12
 */
public class EditTextValidator extends BaseValidator {

    private EditText viewToValidate = null;


    @Override
    public void setViewToValidate(View editText) {
        viewToValidate = (EditText)editText;
    }


    @Override
    public String getTextToValidate() {
        if (viewToValidate == null)
            return null;
        return viewToValidate.getText().toString();
    }


    @Override
    public void displayError(String error) {
        viewToValidate.setError(error);
    }

}
