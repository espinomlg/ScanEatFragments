package com.example.espino.scaneat.adapters;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.interfaces.IHomeListener;
import com.example.espino.scaneat.models.Dish;
import com.example.espino.scaneat.repositories.DishesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class DishItemAdapter extends RecyclerView.Adapter<DishItemAdapter.DishItemViewHolder> {

    private static ArrayList<Dish> searchresults;
    private static IHomeListener activity;

    public DishItemAdapter(IHomeListener callback){
        activity = callback;
        searchresults = new ArrayList<>(DishesRepository.getInstance().getDishesList());
        Collections.sort(searchresults, Dish.sortByRating);
    }


    @Override
    public DishItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_dish, parent, false);

        return new DishItemViewHolder(item);
    }

    @Override
    public void onBindViewHolder(DishItemViewHolder holder, int position) {

        holder.img.setImageResource(searchresults.get(position).getImage());
        holder.name.setText(searchresults.get(position).getName());
        holder.restaurantName.setText(searchresults.get(position).getRestaurantName());
        holder.rating.setText(String.format(Locale.getDefault(),"%s stars",searchresults.get(position).getGlobalRating()));
        holder.price.setText(searchresults.get(position).getFormattedPrice() + "€");


    }


    @Override
    public int getItemCount() {
        return searchresults.size();
    }

    public void sortByRating(){
        Collections.reverse(searchresults);
        notifyDataSetChanged();
    }


    public static class DishItemViewHolder extends RecyclerView.ViewHolder{

        private TextView name, restaurantName, rating, price;
        private ImageView img;

        public DishItemViewHolder(View item) {
            super(item);

            img = (ImageView) item.findViewById(R.id.listitem_dish_image);
            name = (TextView) item.findViewById(R.id.listitem_dish_dishname);
            restaurantName = (TextView) item.findViewById(R.id.listitem_dish_resutaurant);
            rating = (TextView) item.findViewById(R.id.listitem_dish_rating);
            price = (TextView) item.findViewById(R.id.listitem_dish_prices);


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putParcelable(Dish.KEY, searchresults.get(getAdapterPosition()));
                    activity.loadDataFragment(b, null);
                }
            });

        }
    }
}
