package com.example.nitro.tasteme.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nitro.tasteme.R;

import java.lang.reflect.Array;


public class RecipeFragment extends Fragment {

    private LinearLayout ingredientsList;
    private String[] testIngredients = {
            "2 eggs", "200 gr sugar",
            "500 gr flour", "100 gr chocolate",
            "3 drops vanilla", "500 ml milk",
            "80 grams butter",
    };

    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        TextView title = (TextView) rootView.findViewById(R.id.tvRecipeName);
        getActivity().setTitle(title.getText().toString());

        ingredientsList = (LinearLayout) rootView.findViewById(R.id.llRecipeIngredients);

                for (String ingredient : testIngredients) {
            TextView tvIngredient = new TextView(getContext());
            tvIngredient.setText(ingredient);
            ingredientsList.addView(tvIngredient);
        }

        return rootView;
    }
}
