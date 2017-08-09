package com.android.expensetracker.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.expensetracker.R;
import com.android.expensetracker.database.DBHelper;
import com.android.expensetracker.pojo.Item;

public class MainExpenseActivity extends AppCompatActivity {

    View promptView;
    public static final String Email = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expense);
        //check for current date
        //if current date is last date of that month then automatically send an email with the total expense of that  month

    }

    public void addExpense(View view){
        SharedPreferences sharedpreferences = getSharedPreferences(HomeScreenActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String email = sharedpreferences.getString("emailKey","");
        editor.putString(Email, email);
        editor.commit();
        LayoutInflater layoutInflater = LayoutInflater.from(MainExpenseActivity.this);
        promptView = layoutInflater.inflate(R.layout.activity_add_expense, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainExpenseActivity.this);
        alertDialogBuilder.setView(promptView);
        //startActivity(new Intent(MainExpenseActivity.this, MainExpenseActivity.class));
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Add Expense", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        addExpenseOnClick();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }

                });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void viewExpense(View view){

        SharedPreferences sharedpreferences = getSharedPreferences(HomeScreenActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String email = sharedpreferences.getString("emailKey","");
        editor.putString(Email, email);
        editor.commit();
            Intent intent = new Intent(this, ViewExpenseActivity.class);
            startActivity(intent);
            finish();
    }

    public void addExpenseOnClick(){
        EditText et = (EditText)promptView.findViewById(R.id.editText);
        EditText et2 = (EditText)promptView.findViewById(R.id.editText2);
        if(et.getText().toString().trim().equals("") || et2.getText().toString().trim().equals("")){
            Toast.makeText(this,"Empty fields not allowed", Toast.LENGTH_LONG).show();
        }else{
            DBHelper db = new DBHelper(this);
            Log.d("Insert: ", "Inserting ..");
            Item item = new Item();

            String name = et.getText().toString();

            float price = Float.parseFloat( et2.getText().toString());
            DatePicker datePicker;
            datePicker = (DatePicker)promptView.findViewById(R.id.datePicker);
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
            //al.add(item);
            SharedPreferences sharedpreferences = getSharedPreferences(HomeScreenActivity.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            String email = sharedpreferences.getString("email","");
            System.out.println(email);
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
}
