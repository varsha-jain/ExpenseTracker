package com.android.expensetracker.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.android.expensetracker.R;

public class LaunchingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching_screen);
        final int secondsDelayed = 4;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                LayoutInflater layoutInflater = LayoutInflater.from(LaunchingScreenActivity.this);
                View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LaunchingScreenActivity.this);
                alertDialogBuilder.setView(promptView);
                //startActivity(new Intent(LaunchingScreenActivity.this, LaunchingScreenActivity.class));
                //finish();
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(LaunchingScreenActivity.this, HomeScreenActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(LaunchingScreenActivity.this, LoginScreenActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        });

                // create an alert dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        }, secondsDelayed * 1000);
    }
}
