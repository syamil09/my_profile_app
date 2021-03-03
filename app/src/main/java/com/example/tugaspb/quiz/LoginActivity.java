package com.example.tugaspb.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tugaspb.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etName;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setData();
    }

    public void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        etName = findViewById(R.id.etName);
    }

    public void setData() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();

                // action
                Intent intent = new Intent(LoginActivity.this, QuizActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
