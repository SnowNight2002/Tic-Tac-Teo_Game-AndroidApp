package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class player2 extends AppCompatActivity {
    private final List<int[]> combinationsList = new ArrayList<>();

    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes =1;
    private LinearLayout playernameLayout,AILayout;
    private TextView playername,player2name;
    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);
        playername = findViewById(R.id.playername);
        player2name = findViewById(R.id.player2name);

        playernameLayout = findViewById(R.id.playernameLayout);
        AILayout = findViewById(R.id.AILayout);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});

        final String getplayername = getIntent().getStringExtra("playername");
        final String getplayer2name = getIntent().getStringExtra("player2name");

        playername.setText(getplayername);
        player2name.setText(getplayer2name);
        //??????,???????????????
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(0)){
                    performAction((ImageView)v,0);
                }
            }
        });
        //??????,???????????????
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(1)){
                    performAction((ImageView)v,1);
                }
            }
        });
        //??????,???????????????
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(2)){
                    performAction((ImageView)v,2);
                }
            }
        });
        //??????,???????????????
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(3)){
                    performAction((ImageView)v,3);
                }
            }
        });
        //??????,???????????????
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(4)){
                    performAction((ImageView)v,4);
                }
            }
        });
        //??????,???????????????
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(5)){
                    performAction((ImageView)v,5);
                }
            }
        });
        //??????,???????????????
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(6)){
                    performAction((ImageView)v,6);
                }
            }
        });
        //??????,???????????????
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(7)){
                    performAction((ImageView)v,7);
                }
            }
        });
        //??????,???????????????
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelectable(8)){
                    performAction((ImageView)v,8);
                }
            }
        });
    }
    //??????????????????,???????????????ai???????????????
    private void performAction(ImageView imageView,int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;//????????????
        if (playerTurn == 1) {//??????1??????
            imageView.setImageResource(R.drawable.x);//??????????????????x

            if (checkPlayerWin()){//????????????,????????????
                WinDialog2 winDialog2 =new WinDialog2(player2.this,playername.getText().toString()+" has won the match",player2.this);
                winDialog2.setCancelable(false);
                winDialog2.show();
            }
            else if(totalSelectedBoxes == 9 ){//????????????
                WinDialog2 winDialog2 =new WinDialog2(player2.this,"It is a draw!",player2.this);
                winDialog2.setCancelable(false);
                winDialog2.show();
            }else{//????????????,??????+1
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        }else{//??????2 ??????
            imageView.setImageResource(R.drawable.o);//??????????????????o
            if(checkPlayerWin()){//????????????,????????????
                WinDialog2 winDialog2 =new WinDialog2(player2.this,player2name.getText().toString()+" has won the match",player2.this);
                winDialog2.setCancelable(false);
                winDialog2.show();
            }
            else if(totalSelectedBoxes == 9 ){//????????????
                WinDialog2 winDialog2 =new WinDialog2(player2.this,"It is a draw!",player2.this);
                winDialog2.setCancelable(false);
                winDialog2.show();
            }
            else{//????????????,??????+1
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }
    private void changePlayerTurn(int currentPlayerTurn){//????????????????????? ????????????
        playerTurn =currentPlayerTurn;
        if(playerTurn == 1){
            playernameLayout.setBackgroundResource(R.drawable.roundback2);
            AILayout.setBackgroundResource(R.drawable.roundback3);
        }
        else{
            playernameLayout.setBackgroundResource(R.drawable.roundback3);
            AILayout.setBackgroundResource(R.drawable.roundback2);
        }
    }
    private boolean checkPlayerWin(){//??????????????????,??????????????????????????????combinationsList
        boolean response = false;

        for(int i=0;i<combinationsList.size();i++){

            final int [] combination = combinationsList.get(i);

            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn&&boxPositions[combination[2]]== playerTurn){
                response =true;

            }
        }
        return response;
    }


    private boolean isBoxSelectable(int boxPosition){//???????????????????????????
        boolean response = false;
        if (boxPositions[boxPosition] ==0){
            response=true;
        }
        return response;


    }
    public void restartMatch(){ //reset
        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=1;
        long startTime = 0;
        totalSelectedBoxes = 1;
        image1.setImageResource(R.drawable.transparent_back);
        image2.setImageResource(R.drawable.transparent_back);
        image3.setImageResource(R.drawable.transparent_back);
        image4.setImageResource(R.drawable.transparent_back);
        image5.setImageResource(R.drawable.transparent_back);
        image6.setImageResource(R.drawable.transparent_back);
        image7.setImageResource(R.drawable.transparent_back);
        image8.setImageResource(R.drawable.transparent_back);
        image9.setImageResource(R.drawable.transparent_back);
    }
}
