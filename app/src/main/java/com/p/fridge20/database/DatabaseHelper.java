package com.p.fridge20.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Fridge.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_NAME_FRIDGE = "my_products";
    private static final String COLUMN_ID_FRIDGE = "id";
    private static final String COLUMN_NAME_FRIDGE = "product_name";
    private static final String COLUMN_AMOUNT_FRIDGE = "product_amount";
    private static final String COLUMN_PRICE = "product_price";

    private static final String TABLE_NAME_SHOPPING_LIST = "my_product";
    private static final String COLUMN_ID_SHOPPING_LIST = "id";
    private static final String COLUMN_NAME_SHOPPING_LIST = "product_name";
    private static final String COLUMN_AMOUNT_SHOPPING_LIST = "product_amount";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_fridge = "CREATE TABLE " + TABLE_NAME_FRIDGE +
                " (" + COLUMN_ID_FRIDGE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_FRIDGE + " TEXT, " +
                COLUMN_AMOUNT_FRIDGE + " INTEGER)";
        db.execSQL(query_fridge);
        String query_shopping_list = "CREATE TABLE " + TABLE_NAME_SHOPPING_LIST +
                " (" + COLUMN_ID_SHOPPING_LIST + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_SHOPPING_LIST + " TEXT, " +
                COLUMN_AMOUNT_SHOPPING_LIST + " INTEGER)";
        db.execSQL(query_shopping_list);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FRIDGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SHOPPING_LIST);
        onCreate(db);
    }

    public void addProductToFridge(String name, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_FRIDGE, name);
        cv.put(COLUMN_AMOUNT_FRIDGE, amount);

        long result = db.insert(TABLE_NAME_FRIDGE, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void addProductToShoppingList(String name, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_SHOPPING_LIST, name);
        cv.put(COLUMN_AMOUNT_SHOPPING_LIST, amount);

        long result = db.insert(TABLE_NAME_SHOPPING_LIST, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllDataFromFridge() {
        String query = "SELECT * FROM " + TABLE_NAME_FRIDGE;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllDataFromShoppingList() {
        String query = "SELECT * FROM " + TABLE_NAME_SHOPPING_LIST;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_FRIDGE, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(String row_id,String product_name, String product_amount){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_AMOUNT_FRIDGE,product_amount);
        contentValues.put(COLUMN_NAME_FRIDGE,product_name);

        long result = database.update(TABLE_NAME_FRIDGE,contentValues,"id=?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Failed to update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllDataFromShoppingList(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME_SHOPPING_LIST);
    }
}

