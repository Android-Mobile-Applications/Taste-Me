package com.example.nitro.tasteme.data;

import android.provider.BaseColumns;

/**
 * Created by Nitro on 2016-01-16.
 */
public class TasteMeContract {

    public static final class FavouriteRecipesEntry implements BaseColumns {
        public static final String TABLE_NAME = "favourites";

        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_Ingredients = "ingredients";
    }

    public static final class IngredientsEntry implements BaseColumns {
        public static final String TABLE_NAME = "ingredients";

        public static final String COLUMN_PRODUCT = "productName";
        public static  final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_MEASUREMENT = "measurement";
        public static final String COLUMN_RECIPE_ID = "recipeId";
        public static  final String COLUMN_INSHOPPINGCART = "inSoppingCart";
    }
}
