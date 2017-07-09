package com.android.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expense);

    }

    public void addExpense(View view){
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }

    public void viewExpense(View view){
        Intent i = getIntent();
        HashMap<Integer, HashMap<Integer, Float>> map = (HashMap<Integer, HashMap<Integer, Float>>) i.getSerializableExtra("EXTRA_MESSAGE");

        Iterator itr = map.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry p = (Map.Entry)itr.next();
            System.out.println("year;"+p.getKey());
            HashMap<Integer, Float> m1 = map.get(p.getKey());
            Iterator it = m1.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry p1 = (Map.Entry)it.next();
                System.out.println("month:" + p1.getKey()+"  "+ "total:" + p1.getValue());
            }
        }
        //
        Intent intent = new Intent(this, ViewExpense.class);
        intent.putExtra("HashMap", map);
        startActivity(intent);
    }
}
