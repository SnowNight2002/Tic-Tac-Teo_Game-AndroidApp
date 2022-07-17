package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends Activity {

    //int win,lose,draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(new Panel(this)); //show panel
    }
    class Panel extends View {
        public Panel(Context context) {
            super(context);
        }
        int win = getIntent().getIntExtra("win",0); //get win number
        int lose = getIntent().getIntExtra("lose",0);; //get lose number
        int draw = getIntent().getIntExtra("draw",0);; //get draw number

        String title ="your winning status";
        String items[]={"win","lose","draw"};
        float data[] = {win,lose,draw};// set number
        int rColor[]={0xffff0000,0xffffff00,0xff32cd32};// set Color
        float cDegree =0;

        public void onDraw(Canvas c){
            super.onDraw(c);
            Paint paint =new Paint();
            paint.setColor(Color.WHITE);
            c.drawPaint(paint);

            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            for (int i=0;i<data.length;i++){
                float drawDegree = data[i] * 360/10;

                paint.setColor(rColor[i]);
                RectF rec = new RectF(50,100,550,600);
                c.drawArc(rec,cDegree,drawDegree,true,paint);
                cDegree+=drawDegree;
            }
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(50);
            c.drawText(title,20,50,paint);

            int vSpace =getHeight()-100;
            paint.setTextSize(30);
            for(int i =items.length-1;i>=0;i--){
                paint.setColor(rColor[i]);
                c.drawRect(getWidth()-200,vSpace,getWidth()-180,vSpace+20,paint);

                paint.setColor(Color.BLACK);
                c.drawText(items[i],getWidth()-150,vSpace+30,paint);
                vSpace-=40;

            }
        }
    }
}