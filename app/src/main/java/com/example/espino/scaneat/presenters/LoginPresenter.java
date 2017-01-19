package com.example.espino.scaneat.presenters;

import android.content.Context;
import android.text.TextUtils;
import com.example.espino.scaneat.R;
import com.example.espino.scaneat.interfaces.ILogin;



public class LoginPresenter implements ILogin.LoginPresenter {

    private ILogin.View view;
    private Context context;

    public LoginPresenter(ILogin.View view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void validateCredentials(String user, String password) {
        if(TextUtils.isEmpty(user))
            view.setMessage(context.getResources().getString(R.string.field_empty),R.id.login_txiUser);

        else if(TextUtils.isEmpty(password))
            view.setMessage(context.getResources().getString(R.string.field_empty),R.id.login_txiPassword);

        else if(!password.matches(".*(\\d).*"))
            view.setMessage(context.getResources().getString(R.string.password_incorrect_digit),R.id.login_txiPassword);

        else if(!password.matches(".*(\\p{Lower}).*"))
            view.setMessage(context.getResources().getString(R.string.password_incorrect_lower),R.id.login_txiPassword);

        else if(!password.matches(".*(\\p{Upper}).*"))
            view.setMessage(context.getResources().getString(R.string.password_incorrect_upper),R.id.login_txiPassword);

        else if(password.length() < 8)
            view.setMessage(context.getResources().getString(R.string.password_incorrect_length),R.id.login_txiPassword);

        else {
            view.setMessage(context.getResources().getString(R.string.login_correct), 0);
        }
    }
}
