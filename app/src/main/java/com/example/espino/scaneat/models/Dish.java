package com.example.espino.scaneat.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class Dish implements Parcelable{

    public static final String KEY = "dish";

    public static final Comparator<Dish> sortByRating = new Comparator<Dish>() {
        @Override
        public int compare(Dish o1, Dish o2) {
            return Float.compare(o1.getGlobalRating(), o2.getGlobalRating()) * -1;
        }
    };

    public static final Comparator<Dish> sortByPrice = new Comparator<Dish>() {
        @Override
        public int compare(Dish o1, Dish o2) {
            return Float.compare(o1.getPrice(), o2.getPrice());
        }
    };

    private String name,
    restaurantName,
    description,
    ingredients;
    private int image;

    private ArrayList<Comentary> comentaries;

    private float price,
    globalRating,
    presentationRating,
    qualityPriceRating;

    private ArrayList<Allergens> allergens;

    private ArrayList<String> cuisine;


    public Dish(String name, String restaurantName, String description, String ingredients, float price, float globalRating,
                float presentationRating, float qualityPriceRating, ArrayList allergens, ArrayList comentaries,
                ArrayList cuisine, int pathToImg) {
        this.name = name;
        this.restaurantName = restaurantName;
        this.description = description;
        this.ingredients = ingredients;
        this.price = price;
        this.globalRating = globalRating;
        this.presentationRating = presentationRating;
        this.qualityPriceRating = qualityPriceRating;
        this.image = pathToImg;

        this.allergens = allergens;
        this.comentaries = comentaries;
        this.cuisine = cuisine;
    }

    protected Dish(Parcel in) {
        name = in.readString();
        restaurantName = in.readString();
        description = in.readString();
        ingredients = in.readString();
        image = in.readInt();
        price = in.readFloat();
        globalRating = in.readFloat();
        presentationRating = in.readFloat();
        qualityPriceRating = in.readFloat();
        cuisine = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(restaurantName);
        dest.writeString(description);
        dest.writeString(ingredients);
        dest.writeInt(image);
        dest.writeFloat(price);
        dest.writeFloat(globalRating);
        dest.writeFloat(presentationRating);
        dest.writeFloat(qualityPriceRating);
        dest.writeStringList(cuisine);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFormattedPrice() {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(this.price);
    }


    public float getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(float globalRating) {
        this.globalRating = globalRating;
    }

    public float getPresentationRating() {
        return presentationRating;
    }

    public void setPresentationRating(float presentationRating) {
        this.presentationRating = presentationRating;
    }

    public float getQualityPriceRating() {
        return qualityPriceRating;
    }

    public void setQualityPriceRating(float qualityPriceRating) {
        this.qualityPriceRating = qualityPriceRating;
    }

    public Allergens getAllergens(int position) {
        return allergens.get(position);
    }

    public void setAllergens(int position, Allergens allergen) {
        this.allergens.set(position, allergen);
    }

    public Comentary getComentaries(int position) {
        return comentaries.get(position);
    }

    public void setComentaries(int position, Comentary comment) {
        this.comentaries.set(position,comment) ;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<Comentary> getComentaries() {
        return comentaries;
    }

    public void setComentaries(ArrayList<Comentary> comentaries) {
        this.comentaries = comentaries;
    }

    public ArrayList<Allergens> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<Allergens> allergens) {
        this.allergens = allergens;
    }

    public ArrayList<String> getCuisine() {
        return cuisine;
    }

    public void setCuisine(ArrayList<String> cuisine) {
        this.cuisine = cuisine;
    }

}
