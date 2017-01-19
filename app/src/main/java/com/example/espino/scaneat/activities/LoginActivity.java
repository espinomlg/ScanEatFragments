package com.example.espino.scaneat.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.fragments.LoginFragment;
import com.example.espino.scaneat.fragments.SignUpFragment;
import com.example.espino.scaneat.interfaces.LoginListener;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private LoginFragment login;
    private SignUpFragment signup;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState == null) {
            login = LoginFragment.newInstance(null);
            getSupportFragmentManager().beginTransaction().add(R.id.activity_login_framelayout, login).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(int id) {
        FragmentTransaction transaction = null;
        switch (id){
            case R.id.login_btnLogin:
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
                break;

            case R.id.login_btnSignUp:
                signup = SignUpFragment.newInstance(null);
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_login_framelayout, signup).addToBackStack(null).commit();
                break;

            case R.id.signup_btn_signup:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_login_framelayout, login).addToBackStack(null).commit();
                break;
        }

    }

}