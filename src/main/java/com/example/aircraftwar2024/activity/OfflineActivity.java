package com.example.aircraftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.game.EasyGame;
import com.example.myapplication.R;

public class OfflineActivity extends AppCompatActivity  implements View.OnClickListener {
    private boolean having_music;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        having_music = getIntent().getBooleanExtra("music", false);
        setContentView(R.layout.activity_offline);
        Button game_easy = (Button) findViewById(R.id.easy_mode);
        Button game_mid = (Button) findViewById(R.id.normal_mode);
        Button game_hard = (Button) findViewById(R.id.tough_mode);
        game_easy.setOnClickListener(this);
        game_mid.setOnClickListener(this);
        game_hard.setOnClickListener(this);
        intent = new Intent(this, GameActivity.class);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.easy_mode){
            intent.putExtra("level", 1);
            BaseGame.setPattern(1);
            startActivity(intent);
        }else if(view.getId() == R.id.normal_mode){
            intent.putExtra("level", 2);
            BaseGame.setPattern(2);
            startActivity(intent);
        } else if (view.getId() == R.id.tough_mode) {
            intent.putExtra("level", 3);
            BaseGame.setPattern(3);
            startActivity(intent);
        }
    }
}