package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.mmf.R;
import com.mmf.android.validator.EditTextValidator;
import com.mmf.android.validator.checker.LengthChecker;
import com.mmf.android.validator.checker.NotEmptyChecker;
import com.mmf.util.StringUtils;

import java.text.MessageFormat;

/**
 * @author svetlana.voyteh
 * @date: 2/29/12
 */
public class LoginActivity extends Activity{

    private EditText loginEditText;
    private EditText passwordEditText;
    private EditTextValidator loginValidator;
    private EditTextValidator passwordValidator;

//    private StudentOptionService studentOptionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        studentOptionService = new StudentOptionService();

        loginEditText = (EditText)findViewById(R.id.login);
        passwordEditText = (EditText)findViewById(R.id.password);

        loginValidator = new EditTextValidator();
        loginValidator.setViewToValidate(loginEditText);
        loginValidator.addConditionChecker(new NotEmptyChecker(StringUtils.getStringByResource(R.string.validate_messages_login_not_empty)));
        Object[] args = new Object[]{String.valueOf(4), String.valueOf(20)};
        loginValidator.addConditionChecker(new LengthChecker(5, 20, MessageFormat.format(StringUtils.getStringByResource(R.string.validate_messages_login_length), args)));

        passwordValidator = new EditTextValidator();
        passwordValidator.setViewToValidate(passwordEditText);
        passwordValidator.addConditionChecker(new NotEmptyChecker(StringUtils.getStringByResource(R.string.validate_messages_password_not_empty)));
        passwordValidator.addConditionChecker(new LengthChecker(5, 20, MessageFormat.format(StringUtils.getStringByResource(R.string.validate_messages_password_length), args)));

        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(validate()){
//                    try {
//                        studentOptionService.login(login, password);
                        Intent intent = new Intent();
                        intent.putExtra("login", login);
                        setResult(RESULT_OK, intent);
                        finish();
//                    } catch (BusinessLayerException ble) {
//                        Logger.getInstance().error(ble);
//                        Toast.makeText(LoginActivity.this, StringUtils.getStringByResource(R.string.validate_messages_incorrect_login_or_password), Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });
    }

    private boolean validate() {
        String loginErr = loginValidator.performCheck();
        String passErr = passwordValidator.performCheck();
        loginValidator.displayError(loginErr);
        passwordValidator.displayError(passErr);
        return (loginErr == null && passErr == null);
    }


}
