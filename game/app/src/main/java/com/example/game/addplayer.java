package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);
        final EditText PlayerName = findViewById(R.id.playername);  //get textid in player name
        final Button startgameBtn = findViewById(R.id.startGameBtn); //get Button

        startgameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getPlayerName = PlayerName.getText().toString(); //get player name text
                if (getPlayerName.isEmpty()){ //if not input player name send error
                    Toast.makeText(addplayer.this,"Please enter player names",Toast.LENGTH_SHORT).show();

                }else{
                    Intent intent =new Intent(addplayer.this,AI.class); //open AI class
                    intent.putExtra("playername",getPlayerName); //send player name to AI class
                    startActivity(intent);
                }
            }
        });
    }
}