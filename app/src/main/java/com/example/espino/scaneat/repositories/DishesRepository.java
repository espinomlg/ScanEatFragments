package com.example.espino.scaneat.repositories;

import com.example.espino.scaneat.R;
import com.example.espino.scaneat.models.Allergens;
import com.example.espino.scaneat.models.Comentary;
import com.example.espino.scaneat.models.Dish;

import java.util.ArrayList;

/**
 * Created by espino on 17/12/16.
 */

public class DishesRepository {

    public static DishesRepository instance;
    private static ArrayList<Dish> dishesList;
    private static ArrayList<Allergens> allergens;
    private static ArrayList<Comentary> comentaries;
    private static ArrayList<String> cuisine;

    private DishesRepository(){
        dishesList = new ArrayList<>();
        initArrays();

        dishesList.add(new Dish("Uramaki roll", "Asako", "Sushi de calidad", "arroz, algas, pez mantequilla", 8.50f, 4.5f, 4.5f, 5f, allergens, comentaries, cuisine, R.drawable.uramaki_unagi_roll_1));
        dishesList.add(new Dish("Tartar de salmón", "Asako", "Sushi de calidad", "Salmón, aceite y limón", 10.50f, 3f, 4.5f, 5f, allergens, comentaries, cuisine, R.drawable.uramaki_unagi_roll_1));
        dishesList.add(new Dish("Gunkan Tobikko", "Asako", "Sushi de calidad", "arroz, algas, pez mantequilla", 12f, 2.5f, 4.5f, 5f, allergens, comentaries, cuisine, R.drawable.uramaki_unagi_roll_1));
        dishesList.add(new Dish("Escalopines de seitan a la plancha", "Cañadu", "Comida vegetariana en Málaga", "verdura", 5f, 5f, 4.5f, 5f, allergens, comentaries, cuisine, R.drawable.escalopines));
        dishesList.add(new Dish("Hamburguesa de kobe", "Lambuzo", "Hamburguesas gourmet", "pan, carne de ternera, mayonesa", 15f, 4, 4.5f, 5f, allergens, comentaries, cuisine, R.drawable.hamburguesita));
        dishesList.add(new Dish("hamburguesa de buey", "Lambuzo", "Hamburguesas gourmet", "pan, carne de buey, mayonesa", 12f, 3.5f, 4.5f, 5f, allergens, comentaries, cuisine, R.drawable.hamburguesita));
        dishesList.add(new Dish("Noodle de pollo", "Noodles", "Woks variados", "fideos de huevo, pollo, verduras", 7.50f, 1f, 1f, 5f, allergens, comentaries, cuisine, R.drawable.noodles));
        dishesList.add(new Dish("Pizza carbonara", "Tratoria", "Comida italiana para llevar", "pan, tomate, queso, nata, champiñones y bacon", 6f, 5f, 4.5f, 5f, allergens,
                comentaries, cuisine, R.drawable.pizza));
        //TODO 2 extra de prueba para el scroll
        dishesList.add(new Dish("Pizza carbonara", "Tratoria", "Comida italiana para llevar", "pan, tomate, queso, nata, champiñones y bacon", 6f, 5f, 4.5f, 5f, allergens,
                comentaries, cuisine, R.drawable.pizza));
        dishesList.add(new Dish("Pizza carbonara", "Tratoria", "Comida italiana para llevar", "pan, tomate, queso, nata, champiñones y bacon", 6f, 5f, 4.5f, 5f, allergens,
                comentaries, cuisine, R.drawable.pizza));
    }

    private void initArrays(){
        allergens = new ArrayList<>();
        comentaries = new ArrayList<>();
        cuisine = new ArrayList<>();

        allergens.add(new Allergens("Gluten",0));
        allergens.add(new Allergens("Lactosa",0));

        cuisine.add("Asiatica");
        cuisine.add("De fusión");
        cuisine.add("Española");

        comentaries.add(new Comentary("Andrés Espino", "11-12-1990", "Muy bueno el plato y muy buen servicio"));
        comentaries.add(new Comentary("Francisco Luque", "18-12-1990", "El plato no estaba mal pero era un poco caro"));
        comentaries.add(new Comentary("Ramito Quintana", "21-12-1990", "Asqueroso, no volvería ni aunque me pagaran"));
    }


    public static DishesRepository getInstance(){
        if(instance == null)
            instance = new DishesRepository();

        return instance;
    }

    public ArrayList<Dish> getDishesList(){
        return dishesList;
    }
}
