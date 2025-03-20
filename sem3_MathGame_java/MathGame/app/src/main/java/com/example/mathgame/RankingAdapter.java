package com.example.mathgame;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class RankingAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PlayerRank> playerRanks;


    public RankingAdapter(Context context, ArrayList<PlayerRank> playerRanks) {
        this.context = context;
        this.playerRanks = playerRanks;
    }


    @Override
    public int getCount() {
        return playerRanks.size();
    }


    @Override
    public Object getItem(int position) {
        return playerRanks.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.rank_item, parent, false);
        }


        PlayerRank playerRank = playerRanks.get(position);


        TextView nameKeyTextView = convertView.findViewById(R.id.nameKeyTextView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView correctKeyTextView = convertView.findViewById(R.id.correctKeyTextView);
        TextView correctTextView = convertView.findViewById(R.id.correctTextView);
        TextView timeKeyTextView = convertView.findViewById(R.id.timeKeyTextView);
        TextView timeTextView = convertView.findViewById(R.id.timeTextView);


        nameKeyTextView.setText(playerRank.getNameKey());
        nameTextView.setText(playerRank.getName());
        correctKeyTextView.setText(playerRank.getCorrectKey());
        correctTextView.setText(String.valueOf(playerRank.getCorrect()));
        timeKeyTextView.setText(playerRank.getTimeKey());
        timeTextView.setText(String.valueOf(playerRank.getTime()));


        return convertView;
    }
}







