package com.android.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        Intent intent = new Intent(this, ViewExpense.class);
        startActivity(intent);
    }
}
