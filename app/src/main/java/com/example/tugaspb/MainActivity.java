package com.example.tugaspb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tugaspb.calculator.CalculatorActivity;
import com.example.tugaspb.crudapi.CrudApi_MainActivity;
import com.example.tugaspb.crudsqlite.CrudSqlite_MainActivity;
import com.example.tugaspb.quiz.LoginActivity;

public class MainActivity extends AppCompatActivity {
    CardView cardProfile, cardCalculator, cardQuiz, cardCrudApi, cardCrudSqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardProfile = findViewById(R.id.card_profile);
        cardCalculator = findViewById(R.id.card_calculator);
        cardCrudApi = findViewById(R.id.card_crud_api);
        cardQuiz = findViewById(R.id.card_quiz);
        cardCrudSqlite = findViewById(R.id.card_crud_sqlite);

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
        cardQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        cardCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
            }
        });
        cardCrudSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CrudSqlite_MainActivity.class));
            }
        });
        cardCrudApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CrudApi_MainActivity.class));
            }
        });
    }
}
