package com.android.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainExpenseActivity extends AppCompatActivity {

    public static final String Email = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expense);


        //check for current date
        //if current date is last date of that month then automatically send an email with the total expense of that  month

    }

    public void addExpense(View view){
        SharedPreferences sharedpreferences = getSharedPreferences(HomeScreen.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String email = sharedpreferences.getString("emailKey","");
        editor.putString(Email, email);
        editor.commit();
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }

    public void viewExpense(View view){
       // Intent i = getIntent();
       /* HashMap<Integer, HashMap<Integer, Float>> map = (HashMap<Integer, HashMap<Integer, Float>>) i.getSerializableExtra("EXTRA_MESSAGE");
        if(map!=null){
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
       else{*/
        SharedPreferences sharedpreferences = getSharedPreferences(HomeScreen.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String email = sharedpreferences.getString("emailKey","");
        editor.putString(Email, email);
        editor.commit();
            Intent intent = new Intent(this, ViewExpense.class);
            startActivity(intent);
       // }


    }
}
