package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class AI extends AppCompatActivity {
    SQLiteDatabase db;
    String sql;
    Cursor cursor = null;
    String[] GamesLog = {"gameID", "playDate", "playTime","duration", "winningStatus"};
    TextView tvData;
    String dataStr = String.format("%4s %-10s %7s\n", "playDate", "playTime", "winningStatus","duration");
    RadioButton rbSID, rbSName, rbAsc, rbDesc;
    Button btnShow,buttonLeave;

    private  long startTime = 0;
    private  long finishTime =0 ;
    private  int elapsedTime =0 ;

    private final List<int[]> combinationsList = new ArrayList<>();
    private static int[] checkrandom = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes =1;
    private LinearLayout playernameLayout,AILayout;
    private TextView playername,time;
    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = SQLiteDatabase.openDatabase("/data/data/com.example.game/GamesLogDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);

        sql = "CREATE TABLE  IF NOT EXISTS GamesLog (gameID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, playDate text, playTime text, winningStatus text,duration text);";
        db.execSQL(sql);
        db.close();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        startTime = System.currentTimeMillis();

        playername = findViewById(R.id.playername);
        time = findViewById(R.id.time);

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
        //victory condition
        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});
        //get name
        final String getplayername = getIntent().getStringExtra("playername");
        //set name
        playername.setText(getplayername);
        //按下,計勝利條件
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[0] = 1;
            if(isBoxSelectable(0)){
                performAction((ImageView)v,0);

            }
            }
        });
        //按下,計勝利條件
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[1] = 1;
                if(isBoxSelectable(1)){
                    performAction((ImageView)v,1);

                }
            }
        });
        //按下,計勝利條件
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[2] = 1;
                if(isBoxSelectable(2)){
                    performAction((ImageView)v,2);

                }
            }
        });
        //按下,計勝利條件
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[3] = 1;
                if(isBoxSelectable(3)){
                    performAction((ImageView)v,3);

                }
            }
        });
        //按下,計勝利條件
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[4] = 1;
                if(isBoxSelectable(4)){
                    performAction((ImageView)v,4);

                }
            }
        });
        //按下,計勝利條件
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[5] = 1;
                if(isBoxSelectable(5)){
                    performAction((ImageView)v,5);

                }
            }
        });
        //按下,計勝利條件
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[6] = 1;
                if(isBoxSelectable(6)){
                    performAction((ImageView)v,6);

                }
            }
        });
        //按下,計勝利條件
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[7] = 1;
                if(isBoxSelectable(7)){
                    performAction((ImageView)v,7);

                }
            }
        });
        //按下,計勝利條件
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkrandom[8] = 1;
                if(isBoxSelectable(8)){
                performAction((ImageView)v,8);
                }
            }
        });
    }

        private void performAction(ImageView imageView,int selectedBoxPosition) {//檢查勝利條件,切換玩家和ai之間的回合
            boxPositions[selectedBoxPosition] = playerTurn; //設定回合
            if (playerTurn == 1) {//玩家回合
                imageView.setImageResource(R.drawable.x);//按下圖案改為x

                if (checkPlayerWin()){//檢查勝利

                    DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
                    String date = df.format(Calendar.getInstance().getTime());//年月日
                    DateFormat df2 = new SimpleDateFormat("h:mm a");
                    String date2 = df2.format(Calendar.getInstance().getTime());//時間
                    //open Database
                    db = SQLiteDatabase.openDatabase("/data/data/com.example.game/GamesLogDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
                    ContentValues cv = new ContentValues(); //insert date
                    cv.put("playDate", date); // date
                    cv.put("playTime", date2); //date
                    cv.put("winningStatus", "win"); // date
                    finish();//get end game time
                    cv.put("duration", elapsedTime); // date
                    db.insert("GamesLog", null, cv); //insert date
                    time.setText("time is "+elapsedTime);//show time

                    //window show player win
                WinDialog winDialog =new WinDialog(AI.this,playername.getText().toString()+" has won the match",AI.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
                else if(totalSelectedBoxes == 9 ){//打和
                    DateFormat df = new SimpleDateFormat("yyyy.MM.dd");//年月日
                    String date = df.format(Calendar.getInstance().getTime());
                    DateFormat df2 = new SimpleDateFormat("h:mm a");//時間
                    String date2 = df2.format(Calendar.getInstance().getTime());
                    //open Database
                    db = SQLiteDatabase.openDatabase("/data/data/com.example.game/GamesLogDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
                    ContentValues cv = new ContentValues();//insert date
                    cv.put("playDate", date);// date
                    cv.put("playTime", date2);// date
                    cv.put("winningStatus", "draw");// date
                    finish();//get end game time
                    cv.put("duration", elapsedTime);// date
                    db.insert("GamesLog", null, cv);//insert date
                    time.setText("time is "+elapsedTime);//show time
                    //window show It is a draw!
                    WinDialog winDialog =new WinDialog(AI.this,"It is a draw!",AI.this);
                    winDialog.setCancelable(false);
                    winDialog.show();
                }else{//ai回合
                    changePlayerTurn(2);
                    int min = 0;//設定隨機數字
                    int max = 8;//設定隨機數字
                    int random = (int)Math.floor(Math.random()*(max-min+1)+min);//設定隨機
                    while (true){
                        if(checkrandom[random] ==2){//如果玩家沒有按過就離開loop,有就再隨機一個數字
                        break;
                        }
                        random = (int)Math.floor(Math.random()*(max-min+1)+min);
                    }

                    boxPositions[random] = playerTurn; //勝利條件用
                    switch (random) {//空格顯示o
                        case 0:image1.setImageResource(R.drawable.o);checkrandom[0] = 1;break;
                        case 1:image2.setImageResource(R.drawable.o);checkrandom[1] = 1;break;
                        case 2:image3.setImageResource(R.drawable.o);checkrandom[2] = 1;break;
                        case 3:image4.setImageResource(R.drawable.o);checkrandom[3] = 1;break;
                        case 4:image5.setImageResource(R.drawable.o);checkrandom[4] = 1;break;
                        case 5:image6.setImageResource(R.drawable.o);checkrandom[5] = 1;break;
                        case 6:image7.setImageResource(R.drawable.o);checkrandom[6] = 1;break;
                        case 7:image8.setImageResource(R.drawable.o);checkrandom[7] = 1;break;
                        case 8:image9.setImageResource(R.drawable.o);checkrandom[8] = 1;break;
                    }
                    totalSelectedBoxes++;//打和條件+1
                    if(checkPlayerWin()){//檢查勝利條件
                        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");//年月日
                        String date = df.format(Calendar.getInstance().getTime());
                        DateFormat df2 = new SimpleDateFormat("h:mm a");//時間
                        String date2 = df2.format(Calendar.getInstance().getTime());
                        //open DB
                        db = SQLiteDatabase.openDatabase("/data/data/com.example.game/GamesLogDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        ContentValues cv = new ContentValues();//insert date
                        cv.put("playDate", date);
                        cv.put("playTime", date2);
                        cv.put("winningStatus", "fail");
                        finish();//get end game time
                        cv.put("duration", elapsedTime);
                        db.insert("GamesLog", null, cv);//insert date
                        time.setText("time is "+elapsedTime);//show time
                        //window show AI has won the match
                        WinDialog winDialog =new WinDialog(AI.this,"AI has won the match",AI.this);
                        winDialog.setCancelable(false);
                        winDialog.show();
                    }
                    else if(totalSelectedBoxes == 9 ){
                        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
                        String date = df.format(Calendar.getInstance().getTime());
                        DateFormat df2 = new SimpleDateFormat("h:mm a");
                        String date2 = df2.format(Calendar.getInstance().getTime());

                        db = SQLiteDatabase.openDatabase("/data/data/com.example.game/GamesLogDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
                        ContentValues cv = new ContentValues();//insert date
                        cv.put("playDate", date);
                        cv.put("playTime", date2);
                        cv.put("winningStatus", "draw");
                        finish();//get end game time
                        cv.put("duration", elapsedTime);
                        db.insert("GamesLog", null, cv);//insert date
                        time.setText("time is "+elapsedTime);//show time
                        //window show It is a draw!
                        WinDialog winDialog =new WinDialog(AI.this,"It is a draw!",AI.this);
                        winDialog.setCancelable(false);
                        winDialog.show();
                    }
                    else{
                        changePlayerTurn(1);
                        totalSelectedBoxes++;//打和條件+1
                    }
                }
                }
        }
        private void changePlayerTurn(int currentPlayerTurn){//當改變玩家回合 圖標改變
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
        private boolean checkPlayerWin(){//檢查是否勝利,目前已點擊的是否合符combinationsList
                boolean response = false;

                for(int i=0;i<combinationsList.size();i++){

                    final int [] combination = combinationsList.get(i);

                    if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn&&boxPositions[combination[2]]== playerTurn){
                        response =true;

                    }
                }
                return response;
            }


        private boolean isBoxSelectable(int boxPosition){//檢查是否已經被點擊
            boolean response = false;
            if (boxPositions[boxPosition] ==0){
                response=true;
            }
            return response;


        }
        public void restartMatch(){ //reset
        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
        checkrandom =new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2};
        startTime = System.currentTimeMillis();
        playerTurn=1;
        finishTime = 0;
        elapsedTime = 0;
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

    public void finish(){//game time over
        finishTime = System.currentTimeMillis();
        elapsedTime =(int)(finishTime - startTime)/1000;


    }
    public void clickLeave (View view){// click to Leave
        Intent intent =new Intent(AI.this,MainActivity.class);
        startActivity(intent);
    }
}
