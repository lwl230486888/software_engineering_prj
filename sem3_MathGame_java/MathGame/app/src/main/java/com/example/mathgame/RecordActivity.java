package com.example.mathgame;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class RecordActivity extends AppCompatActivity {


    private ListView listView;
    private RecordAdapter recordAdapter;
    private ArrayList<GameRecord> gameRecords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        listView = findViewById(R.id.listView);
        gameRecords = loadGameRecords();


        recordAdapter = new RecordAdapter(this, gameRecords);
        listView.setAdapter(recordAdapter);
    }


    private ArrayList<GameRecord> loadGameRecords() {
        ArrayList<GameRecord> records = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("game_records", MODE_PRIVATE);
        int recordCount = sharedPreferences.getInt("record_count", 0);


        for (int i = 0; i < recordCount; i++) {
            String record = sharedPreferences.getString("record_" + i, "");
            if (!record.isEmpty()) {
                String[] parts = record.split(",");
                String date = parts[0];
                int correctCount = Integer.parseInt(parts[1]);
                int incorrectCount = Integer.parseInt(parts[2]);
                long totalTime = Long.parseLong(parts[3]);
                records.add(new GameRecord(date, correctCount, incorrectCount, totalTime));
            }
        }
        return records;
    }
}







