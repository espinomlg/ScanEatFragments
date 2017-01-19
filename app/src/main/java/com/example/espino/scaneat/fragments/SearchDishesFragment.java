package com.example.espino.scaneat.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.adapters.DishItemAdapter;
import com.example.espino.scaneat.interfaces.IHomeListener;

/**
 * Created by espino on 3/01/17.
 */

public class SearchDishesFragment extends Fragment {

    public static final String TAG = "SEARCHDISHES_FRAGMENT";

    private RecyclerView rcvDishes;
    private DishItemAdapter adapter;

    public static SearchDishesFragment newInstance(Bundle arguments){
        SearchDishesFragment fragment = new SearchDishesFragment();
        if(arguments != null)
            fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DishItemAdapter((IHomeListener) getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_searchresults, container, false);

        if(v != null)
            rcvDishes = (RecyclerView) v.findViewById(R.id.searchresults_rcv);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvDishes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvDishes.setAdapter(adapter);
    }
}
