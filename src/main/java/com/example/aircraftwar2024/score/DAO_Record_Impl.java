package com.example.aircraftwar2024.score;


import com.example.aircraftwar2024.game.BaseGame;

import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class DAO_Record_Impl implements DAO_Record{
    private LinkedList<Record> table;
    public  String path;

    public DAO_Record_Impl(){
        int choice = BaseGame.game_pattern;
        if(choice==1){
            path = "easy.txt";
        }else if(choice==2){
            path = "normal.txt";
        }else if(choice==3) {
            path = "tough.txt";
        }
    }


    @Override
    public void findByName(String aneme) {
        ;
    }

    @Override
    public LinkedList<Record> getAllRecords() {
        return table;
    }

    @Override
    public void doADD(Record newRecord) {
        table.add(newRecord);
        sortByScores();
        for(int i=0; i<table.size(); i++){
            table.get(i).setRanking(i+1);
        }
    }

    @Override
    public void doDelete(String aname) {
        ;
    }

    public void sortByScores(){
        Collections.sort(table, new RecordComparator());
    }


}

class RecordComparator implements Comparator<Record> {
    @Override
    public int compare(Record o1, Record o2) {
        return -1*(o1.getScore()-o2.getScore());
    }
}
