package com.example.nitro.tasteme.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitro.tasteme.MainActivity;
import com.example.nitro.tasteme.R;
import com.example.nitro.tasteme.data.TasteMeContract;
import com.example.nitro.tasteme.data.TasteMeDbHelper;

import org.w3c.dom.Text;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nitro on 2016-01-08.
 */
public class FavouritesFragment extends Fragment{

    TasteMeDbHelper mDbHelper;

    LinearLayout favouriteRecipes;
    private final static Integer[] mThumbIds = {
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_8, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_7,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_8,
                R.drawable.sample_0, R.drawable.sample_1,
                R.drawable.sample_2, R.drawable.sample_3,
                R.drawable.sample_4, R.drawable.sample_5,
                R.drawable.sample_6, R.drawable.sample_4,
                R.drawable.sample_8, R.drawable.sample_7

        };

    OnItemClickedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnItemClickedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onItemClicked(int recipeIndex);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnItemClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnItemClickedListener");
        }

        favouriteRecipes = (LinearLayout) rootView.findViewById(R.id.llFavourites);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1400, 1400);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;

        HashMap<Integer, String> recipeIdTitles = getRecipeTitlesFromSQLite();

        int indexImg = 0;
        Iterator it = recipeIdTitles.entrySet().iterator();
        while (it.hasNext()) {
            final HashMap.Entry pair = (HashMap.Entry)it.next();

            LinearLayout rec = new LinearLayout(getContext());
            rec.setOrientation(LinearLayout.VERTICAL);

            TextView tvRecipeTitle = createRecipeTitleTextView(pair.getValue().toString());
            rec.addView(tvRecipeTitle);

            ImageView img = createRecipeImageView(mThumbIds[indexImg], layoutParams);
            indexImg++;
            rec.addView(img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onItemClicked((int) pair.getKey());
                }
            });

            favouriteRecipes.addView(rec);
        }

        return rootView;
    }

    private ImageView createRecipeImageView(Integer index, LinearLayout.LayoutParams layoutParams) {
        ImageView img = new ImageView(getContext());
        img.setLayoutParams(layoutParams);
        img.setPadding(10, 10, 10, 10);
        img.setImageResource(index);
        return img;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private TextView createRecipeTitleTextView(String title) {
        TextView tvRecipeTitle = new TextView(getContext());
        tvRecipeTitle.setText(title);
        tvRecipeTitle.setTextSize(20);
        tvRecipeTitle.setPadding(10, 100, 20, 60);
        tvRecipeTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        return tvRecipeTitle;
    }

    public HashMap<Integer, String> getRecipeTitlesFromSQLite() {
        mDbHelper = TasteMeDbHelper.getInstance(getContext());

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursorTitles = db.rawQuery("SELECT * FROM " + TasteMeContract.FavouriteRecipesEntry.TABLE_NAME, null);

        HashMap<Integer, String> recipeIdTitles = new HashMap<Integer, String>();

        if(cursorTitles.moveToFirst()) {
            do {
                Integer recipeId = cursorTitles.getInt(
                        cursorTitles.getColumnIndexOrThrow(TasteMeContract.FavouriteRecipesEntry._ID));

                String recipeName = cursorTitles.getString(
                        cursorTitles.getColumnIndexOrThrow(TasteMeContract.FavouriteRecipesEntry.COLUMN_TITLE));
                recipeIdTitles.put(recipeId, recipeName.toString());
            } while (cursorTitles.moveToNext());
        }

        return  recipeIdTitles;
    }
}
