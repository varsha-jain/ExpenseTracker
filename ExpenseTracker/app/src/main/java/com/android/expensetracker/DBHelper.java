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
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "expenseInfo";
    // table name
    private static final String TABLE_ITEMS = "Items";
    private static final String TABLE_USERS = "User";
    //User Table Column names
    private static final String KEY_USERID = "user_id";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_EMAIL_ID = "emailID";
    private static final String KEY_PASSWORD = "password";
    // Items Table Columns names
    private static final String KEY_ID = "item_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_YEAR = "year";
    private static final String KEY_MONTH = "month";
    private static final String KEY_DAY = "day";
    private static final String KEY_USER_ID = "user_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FIRST_NAME + " TEXT," + KEY_LAST_NAME + " TEXT,"
                + KEY_EMAIL_ID + " TEXT," + KEY_PASSWORD + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);


        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
        + KEY_PRICE + " FLOAT," + KEY_YEAR + " INTEGER," + KEY_MONTH + " INTEGER," + KEY_DAY + " INTEGER," + KEY_USER_ID + " INTEGER,"
        + " FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(" + KEY_USERID + ")" + ")";
        sqLiteDatabase.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_ITEMS);
        // Creating tables again
        onCreate(sqLiteDatabase);
    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, user.getFirstName());
        values.put(KEY_LAST_NAME, user.getLastName());
        values.put(KEY_EMAIL_ID, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_PRICE, item.getPrice());
        values.put(KEY_YEAR, item.getYear());
        values.put(KEY_MONTH, item.getMonth());
        values.put(KEY_DAY, item.getDay());
        values.put(KEY_USER_ID, item.getUser_id());
        // Inserting Row
        db.insert(TABLE_ITEMS, null, values);
        db.close(); // Closing database connection
    }

    public String getUser(String emailID, String password){
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String userFirstName = "";
        try{
            String query = "SELECT * FROM "+ TABLE_USERS + " WHERE "+ KEY_EMAIL_ID  + " = " + "'" +
                emailID + "'" + " and " + KEY_PASSWORD + " = " + "'" + password + "'" ;
            cursor = db.rawQuery(query, null);
            if(cursor.getCount()>0) {
                cursor.moveToFirst();
                System.out.println("****INSIDE DBHELPER CLASS GETUSER METHOD*****");
                userFirstName = cursor.getString(1);
            }
            return userFirstName;
        }finally{
            cursor.close();
        }

    }

    public int getUserID(String emailID){
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        int userID = 0;
        try{
            String query = "SELECT * FROM "+ TABLE_USERS + " WHERE "+ KEY_EMAIL_ID  + " = " + "'" +
                    emailID + "'";
            cursor = db.rawQuery(query, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                userID = Integer.parseInt(cursor.getString(0));
            }
            return userID;
        }finally{
            cursor.close();
        }

    }

    public List<Item> getAllItems(int userID, int year, int month) {
        List<Item> itemList = new ArrayList<Item>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS +" where " + KEY_YEAR + " = " + year +" and " + KEY_MONTH + " = " + month
                             + " and " + KEY_USER_ID + " = " + userID;

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
                item.setUser_id(Integer.parseInt(cursor.getString(6)));
// Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

// return contact list
        return itemList;
    }
}
