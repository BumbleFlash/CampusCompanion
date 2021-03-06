package com.example.hp.main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class elgibilityAdapter extends AppCompatActivity {
    int cutoff1,k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);


        cutoff1=getIntent().getIntExtra("Number", 0);
        SQLiteDatabase mydatabase = openOrCreateDatabase("Colleges", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS ANNAUNIV(RANK NUMBER,NAME VARCHAR,CUTOFFG DECIMAL,CITY VARCHAR );");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(1,'Anna University',200,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(2,'PSG College of Technology',199.5,'Coimbatore');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(3,'Madras Institute of Technology',199,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(4,'Coimbatore Institute of Technology',198,'Coimbatore');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(5,'SSN College of Engineering',198,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(6,'Sri Venkateswara College of Engineering',198,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(7,'Chennai Institute of Technology',197,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(8,'St Josephs College of Engineering',196,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(9,'Rajalakshmi Engineering College',195,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(10,'Sri Sai Ram Engineering College',194,'Chennai');");
        mydatabase.execSQL("INSERT INTO ANNAUNIV VALUES(11,'Easwari College Of Engineering',190,'Chennai');");


        if(cutoff1<=200&&cutoff1>=190) {
            Cursor resultset = mydatabase.rawQuery("SELECT RANK,NAME,CITY FROM ANNAUNIV WHERE CUTOFFG<=" + cutoff1, null);
            resultset.moveToFirst();
            k = resultset.getInt(resultset.getColumnIndex("RANK"));

            final ArrayList<topHundred> eli = new ArrayList<topHundred>();

            while (k <= 7) {
                eli.add(new topHundred(resultset.getInt(resultset.getColumnIndex("RANK")), resultset.getString(resultset.getColumnIndex("NAME")), resultset.getString(resultset.getColumnIndex("CITY"))));
                Log.v("Myactivity", resultset.getString(resultset.getColumnIndex("NAME")));
                k++;
                resultset.moveToNext();
            }
            topHundreduniversitiesAdapter Adapter = new topHundreduniversitiesAdapter(this, eli);

            ListView listView = (ListView) findViewById(R.id.list);

            listView.setAdapter(Adapter);
        }
        else {
            Toast.makeText(getApplicationContext(),
                    "Value Should be between 190 to 200", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(elgibilityAdapter.this,eligibilityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            this.finish();


        }
        mydatabase.close();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}