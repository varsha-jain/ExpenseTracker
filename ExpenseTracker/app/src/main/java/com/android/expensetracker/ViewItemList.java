package com.android.expensetracker;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

public class ViewItemList extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_list);
        Intent i = getIntent();
        int year = i.getIntExtra("Year", 0);
        int month = i.getIntExtra("Month", 0);

        DBHelper db = new DBHelper(this);
        List<Item> items = db.getAllItems(year, month);
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, items);

        setListAdapter(adapter);

    }

}
