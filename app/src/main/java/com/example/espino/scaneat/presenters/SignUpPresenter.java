package com.example.espino.scaneat.presenters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.interfaces.ILogin;


public class SignUpPresenter implements ILogin.SignUpPresenter {

    private ILogin.View view;
    private Context context;

    public SignUpPresenter(ILogin.View view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void validateCredentials(String user, String email, String password, String confirm) {
        //validation of the user
        if(TextUtils.isEmpty(user))
            view.setMessage(context.getResources().getString(R.string.field_empty), R.id.signup_txi_username);

        //validation of the email
        else if(TextUtils.isEmpty(email))
            view.setMessage(context.getResources().getString(R.string.field_empty),R.id.signup_txi_email);
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            view.setMessage(context.getResources().getString(R.string.email_incorrect), R.id.signup_txi_email);

        //validation of the password
        else if(TextUtils.isEmpty(password))
            view.setMessage(context.getResources().getString(R.string.field_empty),R.id.signup_txi_passwd);
        else if(!password.matches(".*(\\d).*"))
            view.setMessage(context.getResources().getString(R.string.password_incorrect_digit),R.id.signup_txi_passwd);
        else if(!password.matches(".*(\\p{Lower}).*"))
            view.setMessage(context.getResources().getString(R.string.password_incorrect_lower),R.id.signup_txi_passwd);
        else if(!password.matches(".*(\\p{Upper}).*"))
            view.setMessage(context.getResources().getString(R.string.password_incorrect_upper),R.id.signup_txi_passwd);
        else if(password.length() < 8)
            view.setMessage(context.getResources().getString(R.string.password_incorrect_length),R.id.signup_txi_passwd);

        //validation of the confirm password field
        else if(TextUtils.isEmpty(confirm))
            view.setMessage(context.getResources().getString(R.string.field_empty),R.id.signup_txi_confirmPasswd);
        else if(password.compareTo(confirm) != 0)
            view.setMessage(context.getResources().getString(R.string.passsword_not_equal), R.id.signup_txi_confirmPasswd);

        //when is correct
        else
            view.setMessage("SignUp correct", 0);
    }
}
