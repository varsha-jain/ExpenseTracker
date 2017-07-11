package com.android.expensetracker;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.android.expensetracker.R.id.spinner3;

public class ViewExpense extends AppCompatActivity {

    private int year;
    private int mont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);
        //Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
        /*Intent i = getIntent();

        HashMap<Integer, HashMap<Integer, Float>> map = (HashMap<Integer, HashMap<Integer, Float>>) i.getSerializableExtra("HashMap");
        if(map!=null){
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
            m = map;
        }*/

    }
    public int getMonth(String month){
        HashMap<String, Integer> mon = new HashMap<>();
        mon.put("Jan",1);
        mon.put("Feb",2);
        mon.put("Mar",3);
        mon.put("Apr",4);
        mon.put("May",5);
        mon.put("Jun",6);
        mon.put("Jul",7);
        mon.put("Aug",8);
        mon.put("Sep",9);
        mon.put("Oct",10);
        mon.put("Nov",11);
        mon.put("Dec",12);

        int mo = mon.get(month);
        return mo;
    }
    public void view(View v){
        //if(m!=null){
            Spinner yearSpinner=(Spinner) findViewById(R.id.spinner3);
            year = Integer.parseInt(yearSpinner.getSelectedItem().toString());
            Spinner monthSpinner = (Spinner)findViewById(R.id.spinner4);
            String month = monthSpinner.getSelectedItem().toString();
            mont = getMonth(month);
            //HashMap<Integer, Float> mf = m.get(year);
            //Float total = mf.get(mont);

            DBHelper db = new DBHelper(this);
            Log.d("Reading: ", "Reading all shops..");
            List<Item> items = db.getAllItems(year, mont);
            float sum = 0;
            if(items!=null) {
                for (Item i : items) {
                    System.out.println("item" + i.getName() + " " + "price:" + i.getPrice());
                    sum += i.getPrice();
                }

            }else{
                Toast.makeText(this, "Sorry no results found!",Toast.LENGTH_LONG).show();
            }
            EditText et = (EditText) findViewById(R.id.editText3);
            et.setText(String.valueOf(sum));
        //}
    }
    public void viewItems(View view){
        Intent i = new Intent(this,ViewItemList.class);
        i.putExtra("Year",year);
        i.putExtra("Month", mont);
        startActivity(i);
    }
}
