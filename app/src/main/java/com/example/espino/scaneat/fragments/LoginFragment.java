package com.example.espino.scaneat.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.example.espino.scaneat.R;
import com.example.espino.scaneat.interfaces.ILogin;
import com.example.espino.scaneat.interfaces.LoginListener;
import com.example.espino.scaneat.presenters.LoginPresenter;
import com.example.espino.scaneat.repositories.MyPreferences;


/**
 * Created by espino on 29/12/16.
 */

public class LoginFragment extends Fragment implements ILogin.View {

    public static final String TAG = "LOGIN_FRAGMENT";

    private ILogin.LoginPresenter presenter;
    private TextInputLayout txiUser, txiPasswd;
    private Button login, signup;
    private CheckBox cbremember;
    private LoginListener callback;

    public static LoginFragment newInstance(Bundle arguments) {

        LoginFragment fragment = new LoginFragment();
        if (arguments != null)
            fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (LoginListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        if (v != null) {
            txiUser = (TextInputLayout) v.findViewById(R.id.login_txiUser);
            txiPasswd = (TextInputLayout) v.findViewById(R.id.login_txiPassword);
            login = (Button) v.findViewById(R.id.login_btnLogin);
            signup = (Button)  v.findViewById(R.id.login_btnSignUp);
            cbremember = (CheckBox) v.findViewById(R.id.login_cbRemember);
        }

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateCredentials(txiUser.getEditText().getText().toString(), txiPasswd.getEditText().getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(signup.getId());
            }
        });

        cbremember.setChecked(MyPreferences.getInstance(getContext()).getBoolean(MyPreferences.REMEMBER));

        cbremember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyPreferences.getInstance(getContext()).putBoolean(MyPreferences.REMEMBER, isChecked);
            }
        });

        if(MyPreferences.getInstance(getContext()).getBoolean(MyPreferences.REMEMBER)){
            String passwd = MyPreferences.getInstance(getContext()).getString(MyPreferences.PASSWD);
            txiPasswd.getEditText().setText(passwd == null ? "" : passwd);
        }
    }

    @Override
    public void setMessage(String message, int idView) {
        txiUser.setError(null);
        txiPasswd.setError(null);

        switch (idView) {
            case R.id.login_txiUser:
                txiUser.setError(message);
                txiUser.requestFocus();
                break;

            case R.id.login_txiPassword:
                txiPasswd.setError(message);
                txiPasswd.requestFocus();
                break;

            default:
                if(checkUser(txiUser.getEditText().getText().toString(), txiPasswd.getEditText().getText().toString()))
                    callback.onClick(login.getId());
                else
                    Toast.makeText(getContext(), "Login incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_filters:
                break;

            case R.id.menu_userpreferences:

                break;

            case R.id.menu_generalsettings:

                break;

            case R.id.menu_about:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle(R.string.about);
                dialog.setMessage(R.string.about_body);
                dialog.setPositiveButton(android.R.string.ok, null);
                dialog.show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public boolean checkUser(String username, String password){

        String user = MyPreferences.getInstance(getContext()).getString(MyPreferences.USERNAME),
                passwd = MyPreferences.getInstance(getContext()).getString(MyPreferences.PASSWD);

        if((user != null && username.compareTo(user) == 0) && (passwd != null && password.compareTo(passwd) == 0))
            return true;

        return false;
    }



}
