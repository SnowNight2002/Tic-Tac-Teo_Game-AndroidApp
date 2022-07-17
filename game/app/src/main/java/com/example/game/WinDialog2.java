package com.example.game;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class WinDialog2 extends Dialog {
    private final String message;
    private final player2 player2;

    public WinDialog2(@NonNull Context context, String message, player2 player2) {
        super(context);
        this.message = message;
        this.player2 = player2;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dialog_layout);

        final TextView messageTxt = findViewById(R.id.messageTxt);
        final Button startAgainBtn = findViewById(R.id.startAgainBtn);

        messageTxt.setText(message);//顯示訊息

        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2.restartMatch();//重置
            dismiss();
            }
        });
    }
}
