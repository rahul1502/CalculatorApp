package com.example.rahul.calculatorapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText screen;
    private Button ac, sign, sqrt, division, multiply, minus, plus, clear, equals, point;
    private double op1 = 0, op2 = 0, opbackup;
    private int operation = 0, textFlag = 1, startFlag = 1, equationFlag = 0;
    private Double result;
    private String temp;

    // Discussed algorithms with Malav Patel (B00790747)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (EditText)findViewById(R.id.screen);
        ac = (Button)findViewById(R.id.ac);
        sign = (Button)findViewById(R.id.sign);
        sqrt = (Button)findViewById(R.id.sqrt);
        division = (Button)findViewById(R.id.division);
        multiply = (Button)findViewById(R.id.multiply);
        minus = (Button)findViewById(R.id.minus);
        plus = (Button)findViewById(R.id.plus);
        clear = (Button)findViewById(R.id.clear);
        equals = (Button)findViewById(R.id.equals);
        point = (Button)findViewById(R.id.point);

        screen.setText("0");


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textFlag != 1){
                    if (screen.getText().toString().charAt(0) != '-')
                        screen.setText("-" + screen.getText().toString());
                    else {
                        temp = screen.getText().toString();
                        temp = temp.replace("-", "");
                        screen.setText(temp);
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"Enter a number before setting up the sign(+/-)" , Toast.LENGTH_SHORT).show();
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (screen.getText().toString().indexOf('.') == -1) {
                    if (textFlag == 1 || startFlag == 1) {
                        screen.setText("");
                    }
                    screen.setText(screen.getText().toString() + ".");
                    startFlag = 0;
                    textFlag = 0;
                }
                else if(screen.getText().toString().equals("")) {
                    screen.setText(".");
                    startFlag = 0;
                    textFlag = 0;
                }
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText("0");
                op1 = 0;
                op2 = 0;
                opbackup = 0;
                equationFlag = 0;
                textFlag = 1;
                startFlag = 1;
                plus.setEnabled(true);
                minus.setEnabled(true);
                multiply.setEnabled(true);
                division.setEnabled(true);
                equals.setEnabled(true);
                sqrt.setEnabled(true);
                sign.setEnabled(true);
                plus.setBackgroundResource(R.drawable.round_button2);
                minus.setBackgroundResource(R.drawable.round_button2);
                multiply.setBackgroundResource(R.drawable.round_button2);
                division.setBackgroundResource(R.drawable.round_button2);
            }
        });
        // operation 1 = +
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1) {
                    minus.setEnabled(false);
                    multiply.setEnabled(false);
                    division.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    plus.setBackgroundResource(R.drawable.when_pressed);
                    minus.setBackgroundResource(R.drawable.round_button2);
                    multiply.setBackgroundResource(R.drawable.round_button2);
                    division.setBackgroundResource(R.drawable.round_button2);

                    if(equationFlag == 0) {
                        operation = 1;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{
                        op2 = Double.parseDouble(screen.getText().toString());
                        if(operation==1)
                            result = opbackup + op2;
                        else if(operation==2)
                            result = opbackup - op2;
                        else if(operation==3)
                            result = opbackup * op2;
                        else if(operation==4)
                            result = opbackup / op2;

                        opbackup = result;
                        if(result.toString().length() > 10)
                            screen.setText(result.toString().substring(0,10));
                        else
                            screen.setText(result.toString());
                        textFlag = 1;
                        operation = 1;
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Please enter an operand first" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        // operation 2 = -
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1)  {
                    plus.setEnabled(false);
                    multiply.setEnabled(false);
                    division.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    minus.setBackgroundResource(R.drawable.when_pressed);
                    plus.setBackgroundResource(R.drawable.round_button2);
                    multiply.setBackgroundResource(R.drawable.round_button2);
                    division.setBackgroundResource(R.drawable.round_button2);

                    if(equationFlag == 0) {
                        operation = 2;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{
                        op2 = Double.parseDouble(screen.getText().toString());
                        if(operation==1)
                            result = opbackup + op2;
                        else if(operation==2)
                            result = opbackup - op2;
                        else if(operation==3)
                            result = opbackup * op2;
                        else if(operation==4)
                            result = opbackup / op2;
                        opbackup = result;
                        if(result.toString().length() > 10)
                            screen.setText(result.toString().substring(0,10));
                        else
                            screen.setText(result.toString());


                        textFlag = 1;
                        operation = 2;
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Please enter an operand first" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        // operation 3 = *
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1) {
                    plus.setEnabled(false);
                    minus.setEnabled(false);
                    division.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    multiply.setBackgroundResource(R.drawable.when_pressed);
                    plus.setBackgroundResource(R.drawable.round_button2);
                    minus.setBackgroundResource(R.drawable.round_button2);
                    division.setBackgroundResource(R.drawable.round_button2);

                    if(equationFlag == 0) {
                        operation = 3;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{
                        op2 = Double.parseDouble(screen.getText().toString());
                        if(operation==1)
                            result = opbackup + op2;
                        else if(operation==2)
                            result = opbackup - op2;
                        else if(operation==3)
                            result = opbackup * op2;
                        else if(operation==4)
                            result = opbackup / op2;
                        opbackup = result;
                        if(result.toString().length() > 10)
                            screen.setText(result.toString().substring(0,10));
                        else
                            screen.setText(result.toString());


                        textFlag = 1;
                        operation = 3;
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Please enter an operand first" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        // operation 4 = /
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1){
                    plus.setEnabled(false);
                    minus.setEnabled(false);
                    multiply.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    division.setBackgroundResource(R.drawable.when_pressed);
                    plus.setBackgroundResource(R.drawable.round_button2);
                    minus.setBackgroundResource(R.drawable.round_button2);
                    multiply.setBackgroundResource(R.drawable.round_button2);

                    if(equationFlag == 0) {
                        operation = 4;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{
                        op2 = Double.parseDouble(screen.getText().toString());
                        if(operation==1)
                            result = opbackup + op2;
                        else if(operation==2)
                            result = opbackup - op2;
                        else if(operation==3)
                            result = opbackup * op2;
                        else if(operation==4)
                            result = opbackup / op2;
                        opbackup = result;
                        if(result.toString().length() > 10)
                            screen.setText(result.toString().substring(0,10));
                        else
                            screen.setText(result.toString());

                        textFlag = 1;
                        operation = 4;
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Please enter an operand first" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1) {
                    op1 = Double.parseDouble(screen.getText().toString());
                    result = Math.sqrt(op1);
                    if(result.toString().length() > 10)
                        screen.setText(result.toString().substring(0,10));
                    else
                        screen.setText(result.toString());
                    startFlag = 1;
                }
                else{
                    Toast.makeText(MainActivity.this,"Please enter an operand first" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textFlag = 1;
                startFlag = 1;
                screen.setText("0");
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1) {
                    op2 = Double.parseDouble(screen.getText().toString());
                    if (operation == 1)
                        result = opbackup + op2;
                    else if (operation == 2)
                        result = opbackup - op2;
                    else if (operation == 3)
                        result = opbackup * op2;
                    else if (operation == 4)
                        result = opbackup / op2;

                    opbackup = result;

                    if(result.toString().length() > 10)
                        screen.setText(result.toString().substring(0,10));
                    else
                        screen.setText(result.toString());

                    plus.setEnabled(true);
                    minus.setEnabled(true);
                    multiply.setEnabled(true);
                    division.setEnabled(true);
                    equals.setEnabled(true);
                    sqrt.setEnabled(true);
                    sign.setEnabled(true);
                    plus.setBackgroundResource(R.drawable.round_button2);
                    minus.setBackgroundResource(R.drawable.round_button2);
                    multiply.setBackgroundResource(R.drawable.round_button2);
                    division.setBackgroundResource(R.drawable.round_button2);

                    opbackup = 0;
                    textFlag = 0;
                    equationFlag = 0;
                    startFlag = 1;
                }
                else{
                    Toast.makeText(MainActivity.this,"Please enter an operand first" , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void updateDisplay(View view){

        plus.setEnabled(true);
        minus.setEnabled(true);
        multiply.setEnabled(true);
        division.setEnabled(true);
        equals.setEnabled(true);
        sqrt.setEnabled(true);
        sign.setEnabled(true);

        if(textFlag == 1 || startFlag == 1){
            screen.setText("");
        }
        screen.setText(screen.getText().toString() + ((Button)view).getText());
        textFlag = 0;
        startFlag = 0;
    }

}
