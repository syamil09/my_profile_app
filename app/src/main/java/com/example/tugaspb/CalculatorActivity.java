package com.example.tugaspb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tugaspb.databinding.ActivityCalculatorBindingImpl;

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
    private EditText etDisplay;
    private TextView txtAction;

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
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concate("9");
            }
        });
    }

    public void clickAction() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '+';
                computeCalculation(currentAction, etDisplay.getText().toString());
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '-';
                computeCalculation(currentAction, etDisplay.getText().toString());
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '*';
                computeCalculation(currentAction, etDisplay.getText().toString());
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentAction = '/';
                computeCalculation(currentAction, etDisplay.getText().toString());
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result();
            }
        });
    }

    public void clear() {
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
            display = "";
        }

    }
}
