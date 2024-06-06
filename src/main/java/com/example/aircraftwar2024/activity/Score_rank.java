package com.example.aircraftwar2024.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.R;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score_rank  extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorerank);
        ListView list = (ListView) findViewById(R.id.ListView01);
        SimpleAdapter listItemAdapter = new SimpleAdapter(
                this,
                getData(),
                R.layout.listitem,
                new String[]{"排名","姓名","得分", "时间"},
                new int[]{R.id.rank, R.id.name, R.id.score, R.id.time});

        list.setAdapter(listItemAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String text = "HAHA";
                Toast.makeText(Score_rank.this,text,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<? extends Map<String,?>> getData() {
        ArrayList<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("排名", 1);
        map.put("姓名", "hanfaye");
        map.put("得分", 10);
        map.put("时间", "2024-10-01");
        listitem.add(map);
        return  listitem;
    }


    @Override
    public void onClick(View view) {

    }
}
