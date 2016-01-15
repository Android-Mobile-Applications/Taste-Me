package com.example.nitro.tasteme.fragments;

import android.annotation.TargetApi;
import android.content.Context;
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

import com.example.nitro.tasteme.R;

import org.w3c.dom.Text;

/**
 * Created by Nitro on 2016-01-08.
 */
public class FavouritesFragment extends Fragment{

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
        public void onItemClicked();
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


        for (Integer recipeImg : mThumbIds) {

            LinearLayout rec = new LinearLayout(getContext());
            rec.setOrientation(LinearLayout.VERTICAL);

            TextView tvrecipeTitle = new TextView(getContext());
            tvrecipeTitle.setText("Recipe Title");
            tvrecipeTitle.setTextSize(20);
            tvrecipeTitle.setPadding(10, 100, 20, 60);
            tvrecipeTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            rec.addView(tvrecipeTitle);

            ImageView img = new ImageView(getContext());
            img.setLayoutParams(layoutParams);
            img.setPadding(10, 10, 10, 10);
            img.setImageResource(recipeImg);
            rec.addView(img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onItemClicked();
                }
            });

            favouriteRecipes.addView(rec);
        }




//        GridView gridview = (GridView) rootView.findViewById(R.id.gvFavourites);
//        gridview.setAdapter(new ImageAdapter(getContext()));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(getContext(), "" + position,
//                        Toast.LENGTH_SHORT).show();
//                mCallback.onItemClicked();
//
//            }
//        });

        return rootView;
    }

//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(getContext());
//            //imageView.setLayoutParams(new GridView.LayoutParams(450, 450));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;
//    }
//
//    public class ImageAdapter extends BaseAdapter {
//        private Context mContext;
//
//        public ImageAdapter(Context c) {
//            mContext = c;
//        }
//
//        public int getCount() {
//            return mThumbIds.length;
//        }
//
//        public Object getItem(int position) {
//            return null;
//        }
//
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        // create a new ImageView for each item referenced by the Adapter
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
//            if (convertView == null) {
//                // if it's not recycled, initialize some attributes
//                imageView = new ImageView(mContext);
//                imageView.setLayoutParams(new GridView.LayoutParams(450, 450));
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(8, 8, 8, 8);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//
//            imageView.setImageResource(mThumbIds[position]);
//            return imageView;
//        }
//    }
}
