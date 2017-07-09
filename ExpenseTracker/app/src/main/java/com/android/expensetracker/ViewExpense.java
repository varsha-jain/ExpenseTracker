package com.android.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ViewExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
        Intent i = getIntent();
        HashMap<Integer, HashMap<Integer, Float>> map = (HashMap<Integer, HashMap<Integer, Float>>) i.getSerializableExtra("HashMap");
        Iterator itr = map.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry p = (Map.Entry)itr.next();
            System.out.println("Inside view expense");
            System.out.println("year;"+p.getKey());
            HashMap<Integer, Float> m1 = map.get(p.getKey());
            Iterator i1 = m1.entrySet().iterator();
            while(i1.hasNext()){
                Map.Entry p1 = (Map.Entry)i1.next();
                System.out.println("month:" + p1.getKey()+"  "+ "total:" + p1.getValue());
            }
        }
    }
}
