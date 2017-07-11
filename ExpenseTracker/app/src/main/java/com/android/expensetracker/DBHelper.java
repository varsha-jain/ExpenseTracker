package com.android.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by varsha on 7/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 5;
    // Database Name
    private static final String DATABASE_NAME = "itemsInfo2";
    // Contacts table name
    private static final String TABLE_ITEMS = "Items";
    // Shops Table Columns names
    private static final String KEY_ID = "item_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_YEAR = "year";
    private static final String KEY_MONTH = "month";
    private static final String KEY_DAY = "day";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
        + KEY_PRICE + " FLOAT," + KEY_YEAR + " INTEGER," + KEY_MONTH + " INTEGER," + KEY_DAY + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_ITEMS);
        // Creating tables again
        onCreate(sqLiteDatabase);
    }
    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_PRICE, item.getPrice());
        values.put(KEY_YEAR, item.getYear());
        values.put(KEY_MONTH, item.getMonth());
        values.put(KEY_DAY, item.getDay());

        // Inserting Row
        db.insert(TABLE_ITEMS, null, values);
        db.close(); // Closing database connection
    }

    public List<Item> getAllItems(int year, int month) {
        List<Item> itemList = new ArrayList<Item>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS +" where " + KEY_YEAR + " = " + year +" and " + KEY_MONTH + " = " + month;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setItem_id(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setDay(Integer.parseInt(cursor.getString(5)));
                item.setMonth(Integer.parseInt(cursor.getString(4)));
                item.setPrice(Float.parseFloat(cursor.getString(2)));
                item.setYear(Integer.parseInt(cursor.getString(3)));
// Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

// return contact list
        return itemList;
    }
}
