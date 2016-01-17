package com.example.nitro.tasteme.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitro.tasteme.Ingredient;
import com.example.nitro.tasteme.R;
import com.example.nitro.tasteme.data.TasteMeContract;
import com.example.nitro.tasteme.data.TasteMeDbHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class RecipeFragment extends Fragment {

    TasteMeDbHelper mDbHelper;
    SQLiteDatabase db;
    Integer recipeId;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);


        Bundle bundle = getArguments();
        recipeId = bundle.getInt("recipeId");

        mDbHelper = TasteMeDbHelper.getInstance(getContext());
        db = mDbHelper.getReadableDatabase();

        //Get recipe Info from SQLITE
        getRecipeInfoFromSQLite(db, recipeId, rootView);

        //GET ingredients info from SQLITE
        Cursor cursorRecipeIngr = db.rawQuery("SELECT * FROM " + TasteMeContract.IngredientsEntry.TABLE_NAME +
                " WHERE " + TasteMeContract.IngredientsEntry.COLUMN_RECIPE_ID + " = ?",
                new String[]{recipeId.toString()});
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
         if(cursorRecipeIngr.moveToFirst()) {
            do {

                Ingredient ing = getIngredientsForRecipe(cursorRecipeIngr);
                ingredients.add(ing);
            } while (cursorRecipeIngr.moveToNext());
        }

        cursorRecipeIngr.close();

        //SET Ingredients layout
        LinearLayout ingredientsList = (LinearLayout) rootView.findViewById(R.id.llRecipeIngredients);
        for (Ingredient ingred  : ingredients) {
            LinearLayout llIngredient = createIngredientsLayout(ingred);
            ingredientsList.addView(llIngredient);
        }

        //Add to Shopping Cart



        Button toShoppingCart = (Button) rootView.findViewById(R.id.btnAddFavToShoppingCart);
        toShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToShoppingCart(db, recipeId);
            }
        });

        return rootView;
    }

    private void addToShoppingCart(SQLiteDatabase db, Integer recipeId) {
        Cursor cursorAddIngrToShopping = db.rawQuery("UPDATE " + TasteMeContract.IngredientsEntry.TABLE_NAME +
                        " SET " + TasteMeContract.IngredientsEntry.COLUMN_INSHOPPINGCART + " = ?" +
                        " WHERE " + TasteMeContract.IngredientsEntry.COLUMN_RECIPE_ID + " = ?",
                new String[]{"1", recipeId.toString()});
        cursorAddIngrToShopping.moveToFirst();
        Toast.makeText(getContext(), "Added to Shopping Cart!",
                Toast.LENGTH_SHORT).show();
    }

    private Ingredient getIngredientsForRecipe(Cursor cursorRecipeIngr) {

        Ingredient ing = new Ingredient();
        Integer quantitySQLite = cursorRecipeIngr.getInt(
                cursorRecipeIngr.getColumnIndexOrThrow(TasteMeContract.IngredientsEntry.COLUMN_QUANTITY));

        String productSQLite = cursorRecipeIngr.getString(
                cursorRecipeIngr.getColumnIndexOrThrow(TasteMeContract.IngredientsEntry.COLUMN_PRODUCT));

        String measurementSQLite = cursorRecipeIngr.getString(
                cursorRecipeIngr.getColumnIndexOrThrow(TasteMeContract.IngredientsEntry.COLUMN_MEASUREMENT));

        ing.setProduct(productSQLite);
        ing.setMeasurement(measurementSQLite);
        ing.setQuantity(quantitySQLite);

        return ing;

    }

    private void getRecipeInfoFromSQLite(SQLiteDatabase db, Integer recipeId, View rootView) {

        Cursor cursorRecipe = db.rawQuery("SELECT * FROM " + TasteMeContract.FavouriteRecipesEntry.TABLE_NAME +
                " WHERE " + TasteMeContract.FavouriteRecipesEntry._ID + " = ?", new String[]{recipeId.toString()});

        cursorRecipe.moveToFirst();
        String recipeName = cursorRecipe.getString(
                cursorRecipe.getColumnIndexOrThrow(TasteMeContract.FavouriteRecipesEntry.COLUMN_TITLE));
        TextView title = (TextView) rootView.findViewById(R.id.tvRecipeName);
        title.setText(recipeName);

        String recipeDesc = cursorRecipe.getString(
                cursorRecipe.getColumnIndexOrThrow(TasteMeContract.FavouriteRecipesEntry.COLUMN_DESCRIPTION));
        TextView tvRecDesc = (TextView) rootView.findViewById(R.id.tvRecipeDescription);
        tvRecDesc.setText(recipeDesc);
        cursorRecipe.close();
    }

    private LinearLayout createIngredientsLayout(Ingredient ingred) {

        LinearLayout llIngredient =  new LinearLayout(getContext());
        llIngredient.setOrientation(LinearLayout.HORIZONTAL);

        TextView product = new TextView(getContext());
        product.setPadding(10, 10, 10, 10);

        TextView quantity = new TextView(getContext());
        quantity.setPadding(10, 10, 10, 10);

        TextView measurement = new TextView(getContext());
        measurement.setPadding(10, 10, 10, 10);

        llIngredient.addView(product);
        llIngredient.addView(quantity);
        llIngredient.addView(measurement);

        product.setText(ingred.getProduct());
        quantity.setText(ingred.getQuantity().toString());
        measurement.setText(ingred.getMeasurement());

        return  llIngredient;
    }
}
