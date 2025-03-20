package com.example.mathgame;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class RecordAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<GameRecord> gameRecords;


    public RecordAdapter(Context context, ArrayList<GameRecord> gameRecords) {
        this.context = context;
        this.gameRecords = gameRecords;
    }


    @Override
    public int getCount() {
        return gameRecords.size();
    }


    @Override
    public Object getItem(int position) {
        return gameRecords.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.record_item, parent, false);
        }


        TextView textDate = convertView.findViewById(R.id.textDate);
        TextView textCorrect = convertView.findViewById(R.id.textCorrect);
        TextView textIncorrect = convertView.findViewById(R.id.textIncorrect);
        TextView textTime = convertView.findViewById(R.id.textTime);


        GameRecord gameRecord = gameRecords.get(position);


        textDate.setText(gameRecord.getDate());
        textCorrect.setText("Correct: " + gameRecord.getCorrectCount());
        textIncorrect.setText("Incorrect: " + gameRecord.getIncorrectCount());
        textTime.setText("Time: " + gameRecord.getTotalTime() + "s");


        return convertView;
    }
}







