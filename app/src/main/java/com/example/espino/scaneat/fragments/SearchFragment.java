package com.example.espino.scaneat.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.interfaces.IHomeListener;

/**
 * Created by espino on 30/12/16.
 */

public class SearchFragment extends Fragment {

    public static final String TAG = "SEARCH_FRAGMENT";

    private TextInputLayout txiUbication, txiKeyword;
    private Spinner spnCuisine;
    private CheckBox cbDish, cbRestaurant;
    private Button btn;

    private IHomeListener callback;
    private boolean searchDish;


    public static SearchFragment newInstance(Bundle arguments){
        SearchFragment fragment = new SearchFragment();
        if(arguments != null)
            fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (IHomeListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchDish = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        if(v != null){
            txiUbication = (TextInputLayout) v.findViewById(R.id.search_txi_ubication);
            txiKeyword = (TextInputLayout) v.findViewById(R.id.search_txi_keyword);
            spnCuisine = (Spinner) v.findViewById(R.id.search_spn_cuisine);
            cbDish = (CheckBox) v.findViewById(R.id.search_cb_dish);
            cbRestaurant = (CheckBox) v.findViewById(R.id.search_cb_restaurant);
            btn = (Button) v.findViewById(R.id.search_btn);
        }

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.search_cb_dish:
                        cbDish.setChecked(true);
                        cbRestaurant.setChecked(false);
                        searchDish = true;
                        break;

                    case R.id.search_cb_restaurant:
                        cbDish.setChecked(false);
                        cbRestaurant.setChecked(true);
                        searchDish = false;
                        break;
                }
            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(v.getId());
            }
        });

        loadspnCuisine();
        cbDish.setOnClickListener(listener);
        cbRestaurant.setOnClickListener(listener);

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

    public void loadspnCuisine(){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cuisine,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCuisine.setAdapter(adapter);

    }

    public boolean getSearchDish(){return searchDish;}

}
