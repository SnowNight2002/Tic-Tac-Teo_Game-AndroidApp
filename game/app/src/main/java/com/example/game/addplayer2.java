package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addplayer2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer2);
        final EditText PlayerName = findViewById(R.id.playername);//get textid in player name
        final EditText Player2Name = findViewById(R.id.player2name);//get textid in player name2
        final Button startgameBtn = findViewById(R.id.startGameBtn);//get Button

        startgameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getPlayerName = PlayerName.getText().toString();//get player name text
                final String getPlayer2Name = Player2Name.getText().toString();//get player name text
                if (getPlayerName.isEmpty()&&getPlayer2Name.isEmpty()){ //if not input player name send error
                    Toast.makeText(addplayer2.this,"Please enter player names",Toast.LENGTH_SHORT).show();

                }else{
                    Intent intent =new Intent(addplayer2.this,player2.class); //open palyer2 class
                    intent.putExtra("playername",getPlayerName);//send player name to palyer2 class
                    intent.putExtra("player2name",getPlayer2Name);//send player name to palyer2 class
                    startActivity(intent);
                }
            }
        });
    }
}