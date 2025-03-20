package com.example.mathgame;


public class PlayerRank {
    private String nameKey;
    private String name;
    private String correctKey;
    private int correct;
    private String timeKey;
    private int time;


    public PlayerRank(String nameKey, String name, String correctKey, int correct, String timeKey, int time) {
        this.nameKey = nameKey;
        this.name = name;
        this.correctKey = correctKey;
        this.correct = correct;
        this.timeKey = timeKey;
        this.time = time;
    }


    public String getNameKey() { return nameKey; }
    public String getName() { return name; }
    public String getCorrectKey() { return correctKey; }
    public int getCorrect() { return correct; }
    public String getTimeKey() { return timeKey; }
    public int getTime() { return time; }
}











