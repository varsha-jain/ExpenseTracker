package com.android.expensetracker.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.expensetracker.database.DBHelper;
import com.android.expensetracker.pojo.Item;
import com.android.expensetracker.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AddExpenseActivity extends AppCompatActivity {

    private ArrayList<Item> al = new ArrayList<Item>();
    private HashMap<Integer, HashMap<Integer, Float>> map = new HashMap<Integer, HashMap<Integer, Float>>();
    private HashMap<Integer, Float> mapMonthExpense = new HashMap<Integer,Float>();
    //public static final String EXTRA_MESSAGE = "HashMap";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
    }
    public void addExpenseOnClick(View view){
        EditText et = (EditText)findViewById(R.id.editText);
        EditText et2 = (EditText)findViewById(R.id.editText2);
        if(et.getText().toString().trim().equals("") || et2.getText().toString().trim().equals("")){
            Toast.makeText(this,"Empty fields not allowed", Toast.LENGTH_LONG).show();
        }else{
        DBHelper db = new DBHelper(this);
        Log.d("Insert: ", "Inserting ..");
        Item item = new Item();

        String name = et.getText().toString();

        float price = Float.parseFloat( et2.getText().toString());
        DatePicker datePicker;
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        int   day  = datePicker.getDayOfMonth();
        int   month= datePicker.getMonth() + 1;
        int   year = datePicker.getYear();

        System.out.println(month);
        System.out.println(year);
        System.out.println(day);
        System.out.println(name);
        System.out.println(price);

            item.setDay(day);
            item.setMonth(month);
            item.setName(name);
            item.setPrice(price);
            item.setYear(year);
            al.add(item);
            SharedPreferences sharedpreferences = getSharedPreferences(HomeScreenActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            String email = sharedpreferences.getString("email","");
            System.out.println(email);
            //editor.putString(Email, email);
            //editor.commit();
            int userID = db.getUserID(email);
            System.out.println("in add expense user id is : " + userID);
            item.setUser_id(userID);
            db.addItem(item);
            System.out.println("Record added in db");
            Toast.makeText(this,"Expense added successfully!!!", Toast.LENGTH_LONG).show();
            et.setText("");
            et2.setText("");
        }


    }
    public void backToHomeScreen(View view){

        Intent intent = new Intent(this, MainExpenseActivity.class);
        //intent.putExtra("EXTRA_MESSAGE", map);
        startActivity(intent);
    }
}
