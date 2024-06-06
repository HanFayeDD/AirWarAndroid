package com.example.aircraftwar2024.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.game.EasyGame;
import com.example.aircraftwar2024.game.HardGame;
import com.example.aircraftwar2024.game.MediumGame;
import com.example.myapplication.R;


public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    public Intent intent;

    public Handler mhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            // 处理消息的逻辑
            if(msg.what == -1){
                //加载新的UI
                startActivity(intent);
            }
        }
    };

    private int gameType=0;
    public static int screenWidth,screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getScreenHW();

        if(getIntent() != null){
            gameType = getIntent().getIntExtra("level",1);
        }

        /*TODO:根据用户选择的难度加载相应的游戏界面*/
        BaseGame baseGameView = null;
        if(gameType == 1){
            BaseGame.setPattern(1);
            baseGameView = new EasyGame(this, mhandler);
        } else if (gameType == 2) {
            BaseGame.setPattern(2);
            baseGameView = new MediumGame(this, mhandler);
        } else if (gameType == 3) {
            BaseGame.setPattern(3);
            baseGameView = new HardGame(this, mhandler);
        }

        intent = new Intent(GameActivity.this, Score_rank.class);
        new Thread(baseGameView).start();
        setContentView(baseGameView);
    }

    public void getScreenHW(){
        //定义DisplayMetrics 对象
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getDisplay().getRealMetrics(dm);

        //窗口的宽度
        screenWidth= dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

        Log.i(TAG, "screenWidth : " + screenWidth + " screenHeight : " + screenHeight);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

