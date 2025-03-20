package com.example.mathgame;


public class GameRecord {
    private String date;
    private int correctCount;
    private int incorrectCount;
    private long totalTime;


    public GameRecord(String date, int correctCount, int incorrectCount, long totalTime) {
        this.date = date;
        this.correctCount = correctCount;
        this.incorrectCount = incorrectCount;
        this.totalTime = totalTime;
    }


    public String getDate() {
        return date;
    }


    public int getCorrectCount() {
        return correctCount;
    }


    public int getIncorrectCount() {
        return incorrectCount;
    }


    public long getTotalTime() {
        return totalTime;
    }
}







