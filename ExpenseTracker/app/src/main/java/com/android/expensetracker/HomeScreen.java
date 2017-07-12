package com.android.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }
    public void signUpRedirect(View view){
        //add to DB and then redirect to MainActivity
        DBHelper db = new DBHelper(this);
        EditText etFN  = (EditText)findViewById(R.id.firstName);
        String firstName = etFN.getText().toString();
        EditText etLN = (EditText)findViewById(R.id.lastName);
        String lastName = etLN.getText().toString();
        EditText etEmail = (EditText)findViewById(R.id.emailID);
        String emailID = etEmail.getText().toString();
        EditText etPassword = (EditText)findViewById(R.id.password);
        String password = etPassword.getText().toString();
        User user = new User();
        user.setEmail(emailID);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        db.addUser(user);
        Toast.makeText(this,"You have registered successfully!!!", Toast.LENGTH_LONG).show();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Email, emailID);
        editor.commit();
        Intent intent = new Intent(this, MainExpenseActivity.class);
        startActivity(intent);
    }
    public void loginScreenRedirect(View view){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }
}
