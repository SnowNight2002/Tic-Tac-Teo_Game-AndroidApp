package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Yourscoret extends AppCompatActivity  {
    SQLiteDatabase db; //Database
    Cursor cursor = null; // get Database data
    String[] columns = { "playDate", "playTime", "winningStatus", "duration" }; // get Database data
    String dataStr ; // get Database data
    ListView list; // list
    String [] listItems;  // list
    int win=0,lose=0,draw=0,check=0; // get number
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourscoret);
        list = findViewById(R.id.data); // get id

        try{
            db = SQLiteDatabase.openDatabase("/data/data/com.example.game/GamesLogDB",
                    null, SQLiteDatabase.OPEN_READONLY);// open Database
            cursor =db.rawQuery("SELECT*FROM GamesLog",null); // get all GamesLog data
            cursor =db.query("GamesLog",columns,null,null,null,null,
                    "gameID DESC"); //// get GamesLog data and set orderBy gameID DESC
            dataStr ="";
            listItems = new String[cursor.getCount()]; // set listItems launcher
            while (cursor.moveToNext()){ // get data

                String playDate = cursor.getString(cursor.getColumnIndex("playDate"))+",";
                String playTime = cursor.getString(cursor.getColumnIndex("playTime"))+",";
                String duration = cursor.getString(cursor.getColumnIndex("duration"))+"(s)";
                String winningStatus = cursor.getString(cursor.getColumnIndex("winningStatus"));
                if(winningStatus.equals("win")){
                    win+=1; //use for Panel

                } else if(winningStatus.equals("fail")){
                    lose+=1; //use for Panel

                }else if(winningStatus.equals("draw")) {
                    draw+=1; //use for Panel
                }

                listItems[check] = String.format("%-10s %-10s %-6s %-6s",playDate,playTime,winningStatus,duration);//set [i] date
                check++;
            }
            //set date in ListView
            list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems));

            db.close();//close db


        }catch (Exception e){ //error
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    public void clickHandler (View view){
        int getwin = win; //set
        int getlose = lose; //set
        int getdraw = draw; //set
        Intent intent =new Intent(this,MainActivity2.class);
        intent.putExtra("win",getwin); //send to MainActivity2 class
        intent.putExtra("lose",getlose);//send to MainActivity2 class
        intent.putExtra("draw",getdraw);//send to MainActivity2 class
        startActivity(intent);
    }
}

