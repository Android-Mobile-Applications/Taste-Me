package com.example.nitro.tasteme.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nitro.tasteme.Ingredient;
import com.example.nitro.tasteme.R;
import com.example.nitro.tasteme.data.TasteMeContract;
import com.example.nitro.tasteme.data.TasteMeDbHelper;

import java.util.ArrayList;

import static com.example.nitro.tasteme.R.id.etShoppingItemTitle;
import static com.example.nitro.tasteme.R.id.tvShoppingItemTitleFromRecipe;


/**
 * Created by Nitro on 2016-01-08.
 */
public class ShoppingCartFragment extends Fragment {

    TasteMeDbHelper mDbHelper;
    SQLiteDatabase db;
    ArrayAdapter<String> adapter;
    View rootView;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.lvShoppingCart);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.shopping_item, etShoppingItemTitle);
        listView.setAdapter(adapter);

        Button buttonAddItem = (Button) rootView.findViewById(R.id.btnCreateItem);
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.add("");
            }
        });



        ListView listViewFromRecipe = (ListView) rootView.findViewById(R.id.lvShoppingCartFromRecipes);
        ArrayAdapter<String> adapterFromrecipe = new ArrayAdapter<String>(getContext(), R.layout.shopping_item_from_recipe, tvShoppingItemTitleFromRecipe);
        listViewFromRecipe.setAdapter(adapterFromrecipe);

        mDbHelper = TasteMeDbHelper.getInstance(getContext());
        db = mDbHelper.getReadableDatabase();
        Cursor cursorRecipeIngr = db.rawQuery("SELECT * FROM " + TasteMeContract.IngredientsEntry.TABLE_NAME +
                        " WHERE " + TasteMeContract.IngredientsEntry.COLUMN_INSHOPPINGCART + " = ?",
                new String[]{"1"});
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        if(cursorRecipeIngr.moveToFirst()) {
            do {
                Ingredient ing = getIngredientsForShoppingCart(cursorRecipeIngr);
                ingredients.add(ing);
            } while (cursorRecipeIngr.moveToNext());
        }

        cursorRecipeIngr.close();

        for (Ingredient ingred : ingredients) {
            adapterFromrecipe.add(ingred.getProduct() + " "
                    + ingred.getQuantity() + " "
                    + ingred.getMeasurement());
        }


        Button buttonClearAll = (Button) rootView.findViewById(R.id.btnClear);
        buttonClearAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                adapter.clear();
            }
        });

        return rootView;
    }


    private Ingredient getIngredientsForShoppingCart(Cursor cursorRecipeIngr) {

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


}
