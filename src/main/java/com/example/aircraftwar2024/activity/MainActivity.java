package com.example.aircraftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean having_music;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button begin_game = (Button) findViewById(R.id.button);
        RadioButton bgm_on = (RadioButton) findViewById(R.id.music_on);
        RadioButton bgm_off = (RadioButton) findViewById(R.id.music_off);
        
        begin_game.setOnClickListener(this);
        bgm_on.setOnClickListener(this);
        bgm_off.setOnClickListener(this);

        intent = new Intent(MainActivity.this, OfflineActivity.class);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button){
            intent.putExtra("music", having_music);
            startActivity(intent);
        } else if (view.getId()==R.id.music_on) {
            having_music = true;
        } else if (view.getId()==R.id.music_off) {
            having_music = false;
        }
    }
}