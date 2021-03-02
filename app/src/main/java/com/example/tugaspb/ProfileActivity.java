package com.example.tugaspb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    private Button btnClose, btnQuiz, btnCalculator, btnCrudApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnClose = (Button) findViewById(R.id.btn_close);
        btnQuiz = (Button) findViewById(R.id.btn_quiz);
        btnCalculator = (Button) findViewById(R.id.btn_calculator);
        btnCrudApi = findViewById(R.id.btn_crud_api);

        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showDialog();
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CalculatorActivity.class));
            }
        });


        btnCrudApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CrudApi_MainActivity.class));
            }
        });
    }

    public void closeApp(View view) {
        System.exit(0);
    }

    public void quiz() {

    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

//        set title dialog
        alertDialogBuilder.setTitle("Ingin keluar dari aplikasi?");

//        set message dialog
        alertDialogBuilder
                .setMessage("Klik Ya untuk keluar!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        // close this activity
//                         ProfileActivity.this.finish();
                        finish();
                        // close app

                        moveTaskToBack(true);
                        System.exit(0);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        // create alert dialog from builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show alert dialog
        alertDialog.show();

    }
}
