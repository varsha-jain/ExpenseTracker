package com.android.expensetracker.activities;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.android.expensetracker.R;
import com.android.expensetracker.activities.HomeScreenActivity;
import com.android.expensetracker.database.DBHelper;
import com.android.expensetracker.pojo.Item;

import java.util.List;

public class ViewItemListActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_list);
        Intent i = getIntent();
        int year = i.getIntExtra("Year", 0);
        int month = i.getIntExtra("Month", 0);
        SharedPreferences sharedpreferences = getSharedPreferences(HomeScreenActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String email = sharedpreferences.getString("email","");
        DBHelper db = new DBHelper(this);
        int userID = db.getUserID(email);
        List<Item> items = db.getAllItems(userID, year, month);
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, items);

        setListAdapter(adapter);

    }

}
