package com.example.tugaspb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private QuestionBank QuestionLib = new QuestionBank();
    private int questionNumber = 0;
    private TextView textQuestion, numberQuestion;
    private RadioGroup radioChoice;
    private RadioButton radioChoice1, radioChoice2, radioChoice3, radioChoice4;
    private Button btnNext, btnOut;
    private String answer = "", name;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getUserName();
        btnNext = (Button)findViewById(R.id.btn_next);
        btnOut = (Button)findViewById(R.id.btn_out);
        textQuestion = (TextView)findViewById(R.id.question);
        numberQuestion = (TextView)findViewById(R.id.number_question);
        radioChoice = (RadioGroup)findViewById(R.id.choice);
        radioChoice1 = (RadioButton)findViewById(R.id.choice1);
        radioChoice2 = (RadioButton)findViewById(R.id.choice2);
        radioChoice3 = (RadioButton)findViewById(R.id.choice3);
        radioChoice4 = (RadioButton)findViewById(R.id.choice4);
        updateQuestion();

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getUserName() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
    }

    public void updateQuestion() {

        if (questionNumber < QuestionLib.getLength()) {
            numberQuestion.setText(questionNumber+1+".");
            textQuestion.setText(QuestionLib.getQuestion(questionNumber));
            radioChoice1.setText(QuestionLib.getChoice(questionNumber, 1));
            radioChoice2.setText(QuestionLib.getChoice(questionNumber, 2));
            radioChoice3.setText(QuestionLib.getChoice(questionNumber, 3));
            radioChoice4.setText(QuestionLib.getChoice(questionNumber, 4));
            questionNumber++;
            radioChoice.clearCheck();
        } else {
            Toast.makeText(QuizActivity.this, "Score kamu : "+score, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, ResultQuizActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("totalQuestion", QuestionLib.getLength());
            intent.putExtra("name", name);
            startActivity(intent);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public void next() {
        if (radioChoice.getCheckedRadioButtonId() == -1) {
            Toast.makeText(QuizActivity.this, "Isi jawaban terlebih dahulu!", Toast.LENGTH_SHORT).show();
        } else {
            answer = ((RadioButton)findViewById(radioChoice.getCheckedRadioButtonId())).getText().toString();
//            Toast.makeText(QuizActivity.this, answer, Toast.LENGTH_SHORT).show();
            if (answer == QuestionLib.getCorrectAnswer(questionNumber - 1)) {
                score++;
            }
            updateQuestion();
        }
    }
}
