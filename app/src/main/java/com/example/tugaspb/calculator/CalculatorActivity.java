package com.example.tugaspb.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tugaspb.ProfileActivity;
import com.example.tugaspb.R;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {

    private String display = "0";
    private double valueOne = Double.NaN;
    private double valueTwo;
    private char currentAction = '\0';

    private DecimalFormat df;

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
                    btnClear, btnAdd, btnSubtract, btnMultiply, btnDivide,
                    btnEqual, btnExit;
    private ImageButton btnBackSpace;
    private EditText etDisplay;
    private TextView txtAction;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        df = new DecimalFormat("#.##########");
        init();
        concate(display);
        clickNum();
        clickAction();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.remove));
            }
        });
        btnBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backspace();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CalculatorActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

    }

    public void init() {
        btn0 = (Button)findViewById(R.id.btn_0);
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn3 = (Button)findViewById(R.id.btn_3);
        btn4 = (Button)findViewById(R.id.btn_4);
        btn5 = (Button)findViewById(R.id.btn_5);
        btn6 = (Button)findViewById(R.id.btn_6);
        btn7 = (Button)findViewById(R.id.btn_7);
        btn8 = (Button)findViewById(R.id.btn_8);
        btn9 = (Button)findViewById(R.id.btn_9);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSubtract = (Button)findViewById(R.id.btnSubtract);
        btnMultiply = (Button)findViewById(R.id.btnMultiply);
        btnDivide = (Button)findViewById(R.id.btnDivide);
        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnExit = (Button)findViewById(R.id.btnExit);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnBackSpace = (ImageButton)findViewById(R.id.btnBackSpace);

        etDisplay = (EditText)findViewById(R.id.etDisplay);
        txtAction = (TextView)findViewById(R.id.txtAction);
    }

    public void concate(String num) {
        String etDis = (etDisplay.getText().toString()).replace(',', '.');
//        if (currentAction != '\0') {
//            try {
//                if (valueOne == Double.parseDouble(etDis))
//                    display = num;
//            } catch (Exception e) {}
//        } else if (display.equals("0"))
//            display = num;
//         else
//            display += num;
        display = display.equals("0") ? num : display+num;

        etDisplay.setText(display);
    }

    public void clickNum() {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               concate("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("1");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.do1));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("2");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.re));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("3");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.mi));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("4");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.fa));
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("5");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.sol));
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("6");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.la));
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("7");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.si2));
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("8");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.do2));
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("9");
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.do1));
            }
        });
    }

    public void clickAction() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '+';
                computeCalculation(currentAction, etDisplay.getText().toString());
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.pop1));
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '-';
                computeCalculation(currentAction, etDisplay.getText().toString());
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.pop2));
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '*';
                computeCalculation(currentAction, etDisplay.getText().toString());
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.pop3));
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '/';
                computeCalculation(currentAction, etDisplay.getText().toString());
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.pop4));
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result();
                playSound(MediaPlayer.create(CalculatorActivity.this, R.raw.good_idea_bell));
            }
        });
    }

    public void backspace() {
        if (display.length() > 1) {
            CharSequence currentText = etDisplay.getText();
            display = String.valueOf(currentText.subSequence(0, currentText.length()-1));
            etDisplay.setText(currentText.subSequence(0, currentText.length()-1));
        } else {
            valueOne = Double.NaN;
            valueTwo = Double.NaN;
            display = "0";
            etDisplay.setText("0");
            txtAction.setText("");
        }
    }

    public void clear() {
        valueOne = Double.NaN;
        valueTwo = Double.NaN;
        display = "0";
        etDisplay.setText("0");
        txtAction.setText("");
    }


    public void computeCalculation(char action, String value) {
        if (!value.isEmpty() && action != '\0') {
            value = value.replace(',', '.');
            // if valueOne is exists just change the action
            valueOne = Double.isNaN(valueOne) ? Double.parseDouble(value) : valueOne;
            display = "";
            txtAction.setText(df.format(valueOne)+String.valueOf(action));
            etDisplay.setText("0");
        }
    }

    public void result() {
        valueTwo = Double.parseDouble(etDisplay.getText().toString());
        if (currentAction != '\0') {
            switch (currentAction) {
                case '+' :
                    valueOne = this.valueOne + valueTwo;break;
                case '-' :
                    valueOne = this.valueOne - valueTwo;break;
                case '*' :
                    valueOne = this.valueOne * valueTwo;break;
                case '/' :
                    valueOne = this.valueOne / valueTwo;break;
            }
            etDisplay.setText(df.format(valueOne));
            txtAction.setText("");
            currentAction = '\0';
            valueOne = Double.NaN;
            display = String.valueOf(valueOne);
        }
    }

    public void playSound(MediaPlayer s) {
        if (player != null) {
//            if (player.isPlaying()) {
//                stopSound();
//            }
            stopSound();
        }
        player = s;
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSound();
            }
        });
        player.start();

    }

    public void stopSound() {
        if (player != null) {
            player.release();
            player = null;
        }
    }


}
