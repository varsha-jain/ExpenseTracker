package com.android.expensetracker.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.expensetracker.R;
import com.android.expensetracker.activities.MainExpenseActivity;
import com.android.expensetracker.database.DBHelper;

public class LoginScreenActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void loginScreenRedirect(View view){

        //verify whether valid login or not
        //if valid then allow to next screen else again ask for username and password
        DBHelper db = new DBHelper(this);
        EditText etEmail = (EditText)findViewById(R.id.emailIDLogin);
        String emailID = etEmail.getText().toString();
        EditText etPassword = (EditText)findViewById(R.id.passwordLogin);
        String password = etPassword.getText().toString();
        String firstName = db.getUser(emailID, password);
        System.out.println("first name from db is : " + firstName);
        if(!firstName.trim().equals("")){
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Email, emailID);
            editor.commit();
            Intent intent = new Intent(this, MainExpenseActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Invalid Login credentials! Try again.", Toast.LENGTH_LONG).show();
        }

    }
}
