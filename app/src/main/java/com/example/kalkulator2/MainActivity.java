package com.example.kalkulator2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mainText, secText;
    private String firstNumber = "", secondNumber = "", operator = "";
    private boolean isOperatorPressed = false;
    private boolean isResultDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);
        secText = findViewById(R.id.secondaryText);

        mainText.setVisibility(View.INVISIBLE);
        secText.setVisibility(View.INVISIBLE);

        setupButtons();
    }

    private void setupButtons() {
        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button10);

        Button buttonAdd = findViewById(R.id.button11);
        Button buttonSub = findViewById(R.id.button12);
        Button buttonMul = findViewById(R.id.button13);
        Button buttonDiv = findViewById(R.id.button14);
        Button buttonEqual = findViewById(R.id.button15);
        Button buttonClear = findViewById(R.id.button16);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                onNumberClick(b.getText().toString());
            }
        };

        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                onOperatorClick(b.getText().toString());
            }
        };

        buttonAdd.setOnClickListener(operatorListener);
        buttonSub.setOnClickListener(operatorListener);
        buttonMul.setOnClickListener(operatorListener);
        buttonDiv.setOnClickListener(operatorListener);

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualClick();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCalculator();
            }
        });
    }

    private void onNumberClick(String number) {
        if (isResultDisplayed) {
            clearCalculator();
            isResultDisplayed = false;
        }

        if (isOperatorPressed) {
            secondNumber += number;
            secText.setText(firstNumber + " " + operator + " " + secondNumber);
            mainText.setText(secondNumber);
        } else {
            firstNumber += number;
            mainText.setVisibility(View.VISIBLE);
            mainText.setText(firstNumber);
        }
    }

    private void onOperatorClick(String op) {
        if (isResultDisplayed) {
            isResultDisplayed = false;
        }

        if (!firstNumber.isEmpty()) {
            operator = op;
            isOperatorPressed = true;
            secText.setVisibility(View.VISIBLE);
            secText.setText(firstNumber + " " + operator);
        }
    }

    private void onEqualClick() {
        if (!firstNumber.isEmpty() && !secondNumber.isEmpty()) {
            double result = calculateResult();

            if (result == (int) result) {
                mainText.setText(String.valueOf((int) result));
                secText.setText(firstNumber + " " + operator + " " + secondNumber + " = ");
            } else {
                mainText.setText(String.valueOf(result));
                secText.setText(firstNumber + " " + operator + " " + secondNumber + " = ");
            }

            firstNumber = mainText.getText().toString();
            secondNumber = "";
            isOperatorPressed = false;
            isResultDisplayed = true;
        }
    }

    private double calculateResult() {
        double num1 = Double.parseDouble(firstNumber);
        double num2 = Double.parseDouble(secondNumber);
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }

    private void clearCalculator() {
        firstNumber = "";
        secondNumber = "";
        operator = "";
        isOperatorPressed = false;
        secText.setVisibility(View.INVISIBLE);
        mainText.setVisibility(View.INVISIBLE);
    }
}