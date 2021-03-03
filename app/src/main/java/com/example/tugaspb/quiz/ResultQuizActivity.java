package com.example.tugaspb.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tugaspb.MainActivity;
import com.example.tugaspb.ProfileActivity;
import com.example.tugaspb.R;

public class ResultQuizActivity extends AppCompatActivity {

    private Button btnDone, btnTry;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quiz);

        btnDone = (Button)findViewById(R.id.btn_done);
        btnTry = (Button)findViewById(R.id.btn_try_again);

        TextView txtScore = (TextView)findViewById(R.id.txtScore);
        TextView txtRightAnswer = (TextView)findViewById(R.id.txtRightAnswer);
        TextView txtName = (TextView)findViewById(R.id.txtName);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int totalQuestion = intent.getIntExtra("totalQuestion", 0);
        name = intent.getStringExtra("name");

        txtRightAnswer.setText(score+"/"+totalQuestion);
        txtScore.setText(String.valueOf(100*score/totalQuestion));
        txtName.setText(name);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultQuizActivity.this, MainActivity.class);
                // ResultQuizActivity.this.finish();
                startActivity(intent);

            }
        });

        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultQuizActivity.this, QuizActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
