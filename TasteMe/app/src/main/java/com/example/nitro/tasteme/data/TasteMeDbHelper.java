package com.example.nitro.tasteme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nitro on 2016-01-13.
 */

public class TasteMeDbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "tasteMe.db";

    private static final String SQL_CREATE_FAVOURITES_TABLE = "CREATE TABLE " + TasteMeContract.FavouriteRecipesEntry.TABLE_NAME + " (" +
            TasteMeContract.FavouriteRecipesEntry._ID + " INTEGER PRIMARY KEY, " +
            TasteMeContract.FavouriteRecipesEntry.COLUMN_IMAGE + " BLOB, " +
            TasteMeContract.FavouriteRecipesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            TasteMeContract.FavouriteRecipesEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL " +
            ");";

    private static final String SQL_CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + TasteMeContract.IngredientsEntry.TABLE_NAME + " (" +
            TasteMeContract.IngredientsEntry._ID + " INTEGER PRIMARY KEY, " +
            TasteMeContract.IngredientsEntry.COLUMN_PRODUCT + " TEXT NOT NULL, " +
            TasteMeContract.IngredientsEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
            TasteMeContract.IngredientsEntry.COLUMN_MEASUREMENT + " TEXT NOT NULL, " +
            TasteMeContract.IngredientsEntry.COLUMN_INSHOPPINGCART + " INTEGER, " +
            TasteMeContract.IngredientsEntry.COLUMN_RECIPE_ID + " INTEGER, " +
            "FOREIGN KEY(" + TasteMeContract.IngredientsEntry.COLUMN_RECIPE_ID + ") REFERENCES "
            + TasteMeContract.FavouriteRecipesEntry.TABLE_NAME + "(" + TasteMeContract.FavouriteRecipesEntry._ID +
            "));";


    private static final String SQL_DELETE_FAVOURITES_TABLE =
            "DROP TABLE IF EXISTS " + TasteMeContract.FavouriteRecipesEntry.TABLE_NAME;

    private static final String SQL_DELETE_INGREDIENTS_TABLE =
            "DROP TABLE IF EXISTS " + TasteMeContract.IngredientsEntry.TABLE_NAME;

    public TasteMeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_FAVOURITES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_INGREDIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_FAVOURITES_TABLE);
        db.execSQL(SQL_DELETE_INGREDIENTS_TABLE);
        onCreate(db);
    }

    public void OnDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
