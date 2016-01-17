package com.example.nitro.tasteme;

import android.app.Application;

import com.example.nitro.tasteme.data.parse.models.Ingredient;
import com.example.nitro.tasteme.data.parse.models.Recipe;
import com.parse.Parse;
import com.parse.ParseObject;

// Define class for initializing Parse
public class App extends Application {
    private static final String applicationId = "8IuiYL4O1BYRWvDgSWV97l5zet2RxIrtwv9k8D3c";
    private static final String clientId = "kYYqldFfSdoyoGEUYo81TDbzEibYfyrkywcipxS6";

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Recipe.class);
        ParseObject.registerSubclass(Ingredient.class);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, applicationId, clientId);

        seedInitialData();
    }

    private void seedInitialData() {
        Recipe firstRecipe = new Recipe();
        firstRecipe.setImage("sample_1");
        firstRecipe.setName("Curry Mee");
        firstRecipe.setDescription("Malaysia");
        firstRecipe.setIsCold(false);
        firstRecipe.setIsHot(true);
        firstRecipe.setIsVegetarian(false);
        firstRecipe.setIsSweet(false);
        firstRecipe.setLongitude(4.210484);
        firstRecipe.setLatitude(101.975766);
        firstRecipe.setLikes(47);

        Ingredient firstIngredient = new Ingredient();
        firstIngredient.setProduct("sugar");
        firstIngredient.setQuantity(200);
        firstIngredient.setMeasurement("gr");

        Ingredient secondIngredient = new Ingredient();
        secondIngredient.setProduct("chocolate");
        secondIngredient.setQuantity(100);
        secondIngredient.setMeasurement("gr");

        firstIngredient.put("parent", firstRecipe);
        secondIngredient.put("parent", firstRecipe);

        firstIngredient.saveInBackground();
        secondIngredient.saveInBackground();
    }
}
