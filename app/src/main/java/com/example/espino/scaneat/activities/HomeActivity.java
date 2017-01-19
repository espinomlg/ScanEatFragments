package com.example.espino.scaneat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.fragments.SearchDishesFragment;
import com.example.espino.scaneat.fragments.SearchFragment;
import com.example.espino.scaneat.interfaces.IHomeListener;

/**
 * Created by espino on 29/12/16.
 */

public class HomeActivity extends AppCompatActivity implements IHomeListener{

    private SearchFragment search;
    private SearchDishesFragment dishesFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState == null) {
            search = SearchFragment.newInstance(null);
            getSupportFragmentManager().beginTransaction().add(R.id.activity_home_framelayout, search).commit();
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
            case R.id.search_btn:
                dishesFragment = SearchDishesFragment.newInstance(null);
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_home_framelayout, dishesFragment).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public void loadDataFragment(Bundle args, String fragment) {

    }
}
