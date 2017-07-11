package com.android.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewItemList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item_list);
        Intent i = getIntent();
        int year = i.getIntExtra("Year", 0);
        int month = i.getIntExtra("Month", 0);

        DBHelper db = new DBHelper(this);
        List<Item> items = db.getAllItems(year, month);
        if(items!=null) {
            for (Item item : items) {
                System.out.println("item" + item.getName() + " " + "price:" + item.getPrice());
                setListAdapter();
            }

        }else{
            Toast.makeText(this, "Sorry no results found!",Toast.LENGTH_LONG).show();
        }

    }
}
