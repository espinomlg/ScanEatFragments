package com.example.espino.scaneat.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.interfaces.ILogin;
import com.example.espino.scaneat.interfaces.LoginListener;
import com.example.espino.scaneat.presenters.SignUpPresenter;
import com.example.espino.scaneat.repositories.MyPreferences;

/**
 * Created by espino on 30/12/16.
 */

public class SignUpFragment extends Fragment implements ILogin.View{

    public static final String TAG = "SIGNUP_FRAGMENT";

    private TextInputLayout username,
            email,
            address,
            passwd,
            confirmPasswd;
    private TextView txvRadius;
    private SeekBar radius;
    private CheckBox termsNconditions;
    private Button signup;

    private ILogin.SignUpPresenter presenter;
    private LoginListener callback;


    public static SignUpFragment newInstance(Bundle arguments){
        SignUpFragment fragment = new SignUpFragment();
        if(arguments != null)
            fragment.setArguments(arguments);

        return  fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (LoginListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignUpPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        if(v != null){
            username = (TextInputLayout) v.findViewById(R.id.signup_txi_username);
            email = (TextInputLayout) v.findViewById(R.id.signup_txi_email);
            address = (TextInputLayout) v.findViewById(R.id.signup_txi_address);
            passwd = (TextInputLayout) v.findViewById(R.id.signup_txi_passwd);
            confirmPasswd = (TextInputLayout) v.findViewById(R.id.signup_txi_confirmPasswd);

            signup = (Button) v.findViewById(R.id.signup_btn_signup);
            txvRadius = (TextView) v.findViewById(R.id.signup_txv_radius);
            radius = (SeekBar) v.findViewById(R.id.signup_seekBar);
            termsNconditions = (CheckBox) v.findViewById(R.id.signup_cb_termsNconditions);
        }

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txvRadius.setText(String.format(getResources().getString(R.string.radius), radius.getProgress()));

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateCredentials(username.getEditText().getText().toString(),
                        email.getEditText().getText().toString(),
                        passwd.getEditText().getText().toString(),
                        confirmPasswd.getEditText().getText().toString());
            }
        });

        radius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txvRadius.setText(String.format(getResources().getString(R.string.radius), progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setMessage(String message, int idView) {

        username.setError(null);
        email.setError(null);
        passwd.setError(null);
        confirmPasswd.setError(null);

        switch (idView) {
            case R.id.signup_txi_username:
                username.setError(message);
                username.requestFocus();
                break;
            case R.id.signup_txi_email:
                email.setError(message);
                email.requestFocus();
                break;
            case R.id.signup_txi_passwd:
                passwd.setError(message);
                passwd.requestFocus();
                break;
            case R.id.signup_txi_confirmPasswd:
                confirmPasswd.setError(message);
                confirmPasswd.requestFocus();
                break;
            default:
                MyPreferences.getInstance(getContext()).putString(MyPreferences.USERNAME, username.getEditText().getText().toString());
                MyPreferences.getInstance(getContext()).putString(MyPreferences.EMAIL, email.getEditText().getText().toString());
                MyPreferences.getInstance(getContext()).putString(MyPreferences.PASSWD, confirmPasswd.getEditText().getText().toString());
                Toast.makeText(getContext(), R.string.login_correct, Toast.LENGTH_SHORT).show();
                callback.onClick(signup.getId());
                break;
        }
    }
}
