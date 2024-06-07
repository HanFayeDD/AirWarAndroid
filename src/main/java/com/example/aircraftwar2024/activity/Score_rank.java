package com.example.aircraftwar2024.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.example.aircraftwar2024.game.BaseGame;
import com.example.myapplication.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Score_rank  extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();

    SimpleAdapter listItemAdapter;

    public String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scorerank);
        String temp;
        int c = BaseGame.game_pattern;
        if(c==1){
            temp = "简单模式";
        } else if (c==2) {
            temp = "普通模式";
        } else if (c==3) {
            temp = "困难模式";
        }
        else{
            temp = "排行榜";
        }
        TextView title = findViewById(R.id.text);
        title.setText(temp);

        getFileName();
        init_and_add(getIntent().getIntExtra("score", -1));
        getData();
        ListView list = (ListView) findViewById(R.id.ListView01);
        listItemAdapter = new SimpleAdapter(
                this,
                listitem,
                R.layout.listitem,
                new String[]{"排名","姓名","得分", "时间"},
                new int[]{R.id.rank, R.id.name, R.id.score, R.id.time});

        list.setAdapter(listItemAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String text = "选中" + "第" + (arg2+1) + "项";
                Toast.makeText(Score_rank.this,text,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(Score_rank.this);
                builder.setTitle("确认删除?");
                builder.setMessage("确定删除该条游戏记录吗?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listitem.remove(arg2);
                        sortbyscore();
                        listItemAdapter.notifyDataSetChanged();
                        wirtetotxt();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后的处理逻辑;
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        Button gameback = (Button) findViewById(R.id.backgame);
        gameback.setOnClickListener(this);

    }

    private  void getFileName(){
        int c = BaseGame.game_pattern;
        if(c==1){
            filename = "easy.txt";
        } else if (c==2) {
            filename = "normal.txt";
        } else if (c==3) {
            filename = "tough.txt";
        }
    }
    @SuppressWarnings("unchecked")
    private void init_and_add(int game_score){
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)));
            Object obj = ois.readObject();
            if(obj instanceof ArrayList){
                listitem = (ArrayList<Map<String,Object>>)obj;
            }
            ois.close();
        } catch (IOException e) {
            listitem = new ArrayList<Map<String, Object>>();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            String new_time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("排名", -1);
            map.put("姓名", "test");
            map.put("得分", game_score);
            map.put("时间", new_time);
            listitem.add(map);
            sortbyscore();
            wirtetotxt();
        }
    }

    private void sortbyscore(){
        Collections.sort(listitem, new ScoreCp());
        int i = 1;
        for(Map<String, Object> ele : listitem){
            ele.put("排名", i);
            i++;
        }
    }


    private void getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("排名", 10);
        map.put("姓名", "hanfaye");
        map.put("得分", 10);
        map.put("时间", "2024-10-01");
        listitem.add(map);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("排名", 70);
        map1.put("姓名", "dry1");
        map1.put("得分", 30);
        map1.put("时间", "2024-10-01");
        listitem.add(map1);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("排名", 70);
        map2.put("姓名", "ff1");
        map2.put("得分", 67);
        map2.put("时间", "2024-10-01");
        listitem.add(map2);
        sortbyscore();
    }
    private void wirtetotxt(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)));
            oos.writeObject(listitem);
            oos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            // System.out.println("out_finally");
            ;

        }
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.backgame){
            setContentView(R.layout.activity_main);
        }
    }
}

class ScoreCp implements Comparator<Map<String, Object>>{
    @Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        return  -1*((int)o1.get("得分")-(int)o2.get("得分"));
    }
}
