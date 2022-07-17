package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView leavet,rankt,player2t,playAIt,Yourscoret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // defining cards
        leavet = (CardView) findViewById(R.id.leave);
        rankt = (CardView) findViewById(R.id.rank);
        player2t = (CardView) findViewById(R.id.player2);
        playAIt = (CardView) findViewById(R.id.playAI);
        Yourscoret = (CardView) findViewById(R.id.Yourscore);
        //add click listener to the cards
        leavet.setOnClickListener(this);
        rankt.setOnClickListener(this);
        player2t.setOnClickListener(this);
        playAIt.setOnClickListener(this);
        Yourscoret.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()){//點擊以進入對應的class
            case R.id.leave: finish();System.exit(0);break;
            case R.id.playAI: i = new Intent(this,addplayer.class);startActivity(i);break;
            case R.id.player2: i = new Intent(this,addplayer2.class);startActivity(i);break;
            case R.id.rank: i = new Intent(this,ranking.class);startActivity(i);break;
            case R.id.Yourscore: i = new Intent(this,Yourscoret.class);startActivity(i);break;
            default:break;
        }
    }







}