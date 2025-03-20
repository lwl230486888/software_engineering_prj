package com.example.mathgame;




import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;




public class GameActivity extends AppCompatActivity {




    private TextView textQuestionNumber;
    private TextView textQuestion;
    private EditText editTextNumber;
    private Button buttonSubmit;
    private TextView resultTextView;
    private TextView correctAnswerTextView;
    private TextView textTime;
    private Button buttonNext;




    private int currentQuestionNumber = 1;
    private final int totalQuestions = 10;
    private int number1 = 0;
    private int number2 = 0;
    private String operator = "";
    private int correctAnswer = 0;
    private int correctCount = 0;
    private int incorrectCount = 0;
    private long startTime;
    private long totalTime;




    private Handler handler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            updateTimer();
            handler.postDelayed(this, 1000);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);




        textQuestionNumber = findViewById(R.id.textQuestionNumber);
        textQuestion = findViewById(R.id.textQuestion);
        editTextNumber = findViewById(R.id.editTextNumber);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        resultTextView = findViewById(R.id.Result);
        correctAnswerTextView = findViewById(R.id.CorrectAnswer);
        textTime = findViewById(R.id.textTime);
        buttonNext = findViewById(R.id.buttonNext);




        startTime = SystemClock.elapsedRealtime();
        generateQuestion();


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionNumber < totalQuestions) {
                    currentQuestionNumber++;
                    generateQuestion();
                } else {
                    totalTime = (SystemClock.elapsedRealtime() - startTime) / 1000;
                    saveGameRecord();
                    textQuestion.setText("Quiz finished!");
                    resultTextView.setText("Correct: " + correctCount + ", Incorrect: " + incorrectCount);
                    correctAnswerTextView.setText("Total Time: " + totalTime + "s");
                    resultTextView.setVisibility(View.VISIBLE);
                    correctAnswerTextView.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.GONE);
                    buttonSubmit.setVisibility(View.GONE);
                    editTextNumber.setVisibility(View.GONE);
                    textTime.setVisibility(View.GONE);
                    handler.removeCallbacks(timerRunnable);


                    // Redirect to RecordActivity
                    Intent intent = new Intent(GameActivity.this, RecordActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void saveGameRecord() {
        SharedPreferences sharedPreferences = getSharedPreferences("game_records", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String record = date + "," + correctCount + "," + incorrectCount + "," + totalTime;


        int recordCount = sharedPreferences.getInt("record_count", 0);
        editor.putString("record_" + recordCount, record);
        editor.putInt("record_count", recordCount + 1);
        editor.apply();
    }






    @Override
    protected void onResume() {
        super.onResume();
        handler.post(timerRunnable);
    }




    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(timerRunnable);
    }




    private void generateQuestion() {
        Random random = new Random();
        number1 = random.nextInt(101);
        number2 = random.nextInt(101);




        // 确保除法结果是整数
        while (number2 == 0 || number1 % number2 != 0) {
            number2 = random.nextInt(101);
        }




        // 确保减法结果为非负数
        while (operator.equals("-") && number1 < number2) {
            number1 = random.nextInt(101);
            number2 = random.nextInt(101);
        }




        String[] operators = {"+", "-", "*", "/"};
        operator = operators[random.nextInt(operators.length)];




        switch (operator) {
            case "+":
                correctAnswer = number1 + number2;
                break;
            case "-":
                correctAnswer = number1 - number2;
                break;
            case "*":
                correctAnswer = number1 * number2;
                break;
            case "/":
                if (number2 != 0) {
                    correctAnswer = number1 / number2;
                } else {
                    correctAnswer = 0; // 避免除以零
                }
                break;
        }




        textQuestionNumber.setText("Q : " + currentQuestionNumber + " / " + totalQuestions);
        textQuestion.setText(number1 + " " + operator + " " + number2 + " = ?");
        editTextNumber.setText("");
        resultTextView.setVisibility(View.GONE);
        correctAnswerTextView.setVisibility(View.GONE);
        buttonNext.setVisibility(View.GONE);
        buttonSubmit.setVisibility(View.VISIBLE);
    }




    private void checkAnswer() {
        String userAnswerString = editTextNumber.getText().toString();
        int userAnswer;
        try {
            userAnswer = Integer.parseInt(userAnswerString);
        } catch (NumberFormatException e) {
            userAnswer = -1; // 用户未输入有效数字时设为-1
        }




        if (userAnswer == correctAnswer) {
            resultTextView.setText("Correct!");
            correctCount++;
        } else {
            resultTextView.setText("Incorrect!");
            incorrectCount++;
        }




        correctAnswerTextView.setText("Answer is: " + correctAnswer);
        resultTextView.setVisibility(View.VISIBLE);
        correctAnswerTextView.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);
        buttonSubmit.setVisibility(View.GONE);
    }




    private void updateTimer() {
        long elapsedTime = (SystemClock.elapsedRealtime() - startTime) / 1000;
        textTime.setText("Time: " + elapsedTime + "s");
    }






}















