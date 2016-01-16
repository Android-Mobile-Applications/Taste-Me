package com.example.nitro.tasteme.fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitro.tasteme.R;
import com.example.nitro.tasteme.data.TasteMeContract;
import com.example.nitro.tasteme.data.TasteMeDbHelper;

/**
 * Created by Nitro on 2016-01-15.
 */
public class HomeRecipeFragment extends Fragment {

    private LinearLayout ingredientsList;
    private LinearLayout llIngredient;
    private TextView product;
    private TextView quantity;
    private TextView measurement;

    private String[] testProducts = {
            "eggs", "sugar", "flour", "chocolate", "milk", "butter"
    };

    private Integer[] testQuantity = {
            2, 200, 500, 100, 400, 80
    };

    private String[] testMeasurements = {
            "", "gr", "gr", "gr", "ml", "gr"
    };

    public HomeRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home_recipe, container, false);

        TextView title = (TextView) rootView.findViewById(R.id.tvHomeRecipeName);
        getActivity().setTitle(title.getText().toString());

        ingredientsList = (LinearLayout) rootView.findViewById(R.id.llHomeRecipeIngredients);

        for(int i = 0; i < testProducts.length; i++){

            llIngredient =  new LinearLayout(getContext());
            llIngredient.setOrientation(LinearLayout.HORIZONTAL);
            product = new TextView(getContext());
            product.setPadding(10, 10, 10, 10);
            quantity = new TextView(getContext());
            quantity.setPadding(10, 10, 10, 10);
            measurement = new TextView(getContext());
            measurement.setPadding(10, 10, 10, 10);
            llIngredient.addView(product);
            llIngredient.addView(quantity);
            llIngredient.addView(measurement);

            product.setText(testProducts[i]);
            quantity.setText(testQuantity[i].toString());
            measurement.setText(testMeasurements[i]);

            ingredientsList.addView(llIngredient);

        }



        final ImageButton addToFavourites = (ImageButton) rootView.findViewById(R.id.btnAddToFavourites);
        addToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView recipeNameView = (TextView) rootView.findViewById(R.id.tvHomeRecipeName);
                String recipeTitle = recipeNameView.getText().toString();

                TextView recipeDescView = (TextView) rootView.findViewById(R.id.tvHomeRecipeDescription);
                String recipeDescription = recipeDescView.getText().toString();

                TasteMeDbHelper mDbHelper = new TasteMeDbHelper(getContext());

                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(TasteMeContract.FavouriteRecipesEntry.COLUMN_TITLE, recipeTitle);
                values.put(TasteMeContract.FavouriteRecipesEntry.COLUMN_DESCRIPTION, recipeDescription);

                long newRowId;
                newRowId = db.insert(
                        TasteMeContract.FavouriteRecipesEntry.TABLE_NAME,
                        TasteMeContract.FavouriteRecipesEntry.COLUMN_IMAGE,
                        values);

                Toast.makeText(getContext(), "" + newRowId,
                        Toast.LENGTH_SHORT).show();


                for(int i = 0; i < ingredientsList.getChildCount(); i++){

                    LinearLayout llChild = (LinearLayout) ingredientsList.getChildAt(i);
                    TextView tvProduct = (TextView) llChild.getChildAt(0);
                    TextView tvQuantity = (TextView) llChild.getChildAt(1);
                    TextView tvMeasurement = (TextView) llChild.getChildAt(2);

                    ContentValues valuesIngredients = new ContentValues();
                    valuesIngredients.put(TasteMeContract.IngredientsEntry.COLUMN_PRODUCT, tvProduct.getText().toString());
                    valuesIngredients.put(TasteMeContract.IngredientsEntry.COLUMN_QUANTITY, Integer.parseInt(tvQuantity.getText().toString()));
                    valuesIngredients.put(TasteMeContract.IngredientsEntry.COLUMN_MEASUREMENT, tvMeasurement.getText().toString());
                    valuesIngredients.put(TasteMeContract.IngredientsEntry.COLUMN_INSHOPPINGCART, 0);
                    valuesIngredients.put(TasteMeContract.IngredientsEntry.COLUMN_RECIPE_ID, newRowId);

                    long ingrNewRowId;
                    ingrNewRowId = db.insert(
                            TasteMeContract.IngredientsEntry.TABLE_NAME,
                            null,
                            valuesIngredients);

                    Toast.makeText(getContext(), "" + ingrNewRowId + product.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}

