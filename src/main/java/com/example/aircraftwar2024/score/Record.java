package com.example.aircraftwar2024.score;
import java.io.Serializable;
public class Record implements  Serializable{
    private int ranking=-1;
    private String name="test";
    private int score;
    private String time;
    public Record(int aranking, String aname, int ascore, String atime){
        ranking = aranking;
        name = aname;
        score = ascore;
        time = atime;
    }
    public int getRanking() {
        return ranking;
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public String getTime() {
        return time;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
