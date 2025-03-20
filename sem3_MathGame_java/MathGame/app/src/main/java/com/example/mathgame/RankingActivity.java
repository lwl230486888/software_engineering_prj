package com.example.mathgame;


import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class RankingActivity extends AppCompatActivity {


    private ListView listView;
    private RankingAdapter rankingAdapter;
    private ArrayList<PlayerRank> playerRanks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);


        listView = findViewById(R.id.listView);
        playerRanks = new ArrayList<>();
        loadPlayerRanks();
        Collections.sort(playerRanks, new ScoreComparator());


        rankingAdapter = new RankingAdapter(this, playerRanks);
        listView.setAdapter(rankingAdapter);
    }


    private void loadPlayerRanks() {
        String json;
        try {
            InputStream is = getAssets().open("ranking.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


            Log.d("RankingActivity", "JSON loaded: " + json);


            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nameKey = "Name";
                String name = jsonObject.getString("Name");
                String correctKey = "Correct";
                int correct = jsonObject.getInt("Correct");
                String timeKey = "Time";
                int time = jsonObject.getInt("Time");


                playerRanks.add(new PlayerRank(nameKey, name, correctKey, correct, timeKey, time));
            }


            Log.d("RankingActivity", "Player ranks loaded: " + playerRanks.size());


        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e("RankingActivity", "Error loading JSON", e);
        }
    }














    // Comparator to sort PlayerRank objects based on score
    class ScoreComparator implements Comparator<PlayerRank> {
        @Override
        public int compare(PlayerRank o1, PlayerRank o2) {
            return o2.getCorrect() - o1.getCorrect(); // Descending order
        }
    }
}







