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
    // op1 - operand 1
    // op2 - operand 2
    // opbackup - take backup of the result of the operation between op1 and op2
    private int operation = 0, textFlag = 1, startFlag = 1, equationFlag = 0, equalsFlag = 0;
    // FLAGS
    //      operation - shows the type of operation that the user is performing
    //      textFlag - specifies whether the user is in typing mod (entering input numbers) or entering operations
    //      startFlag - specifies whether the user started adding numbers as part of the input or not (initially startFlag = 1 but as the user
    //                  starts entering numbers, it changes to 0)
    //      equationFlag - detects whether the operation is over or not (distinguishes between two different operations)
    //      equalsFlag - for continuous operation using equals button
    private Double result;
    private String temp;

    // Discussed algorithms with Malav Patel (B00790747)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the views
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

        // set 0 initially on the screen
        screen.setText("0");

        // + or - sign of the operand
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textFlag != 1){
                    // if no '-' sign in front of the operand then add '-' sign
                    if (screen.getText().toString().charAt(0) != '-')
                        screen.setText("-" + screen.getText().toString());
                    else {
                        // if already there is a '-' sign in front of the operand, then remove it
                        temp = screen.getText().toString();
                        temp = temp.replace("-", "");
                        screen.setText(temp);
                    }
                }
                else
                    Toast.makeText(MainActivity.this,"Enter a number before setting up the sign(+/-)" , Toast.LENGTH_SHORT).show();
            }
        });

        // add decimal point
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if there is already one decimal point in the operand
                if (screen.getText().toString().indexOf('.') == -1) {
                    if (textFlag == 1 || startFlag == 1) {
                        screen.setText("");
                    }
                    // add decimal point in the operand
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

        // set all the flags to their default values
        // enable all the buttons
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
                    // disable all the button except the main operation button
                    minus.setEnabled(false);
                    multiply.setEnabled(false);
                    division.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    // change the color of the main operation button
                    plus.setBackgroundResource(R.drawable.when_pressed);
                    minus.setBackgroundResource(R.drawable.round_button2);
                    multiply.setBackgroundResource(R.drawable.round_button2);
                    division.setBackgroundResource(R.drawable.round_button2);

                    // if equation is not continue
                    if(equationFlag == 0) {
                        operation = 1;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{ // if equation is continue
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

                        // only show first 10 digits
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
                    // disable all the button except the main operation button
                    plus.setEnabled(false);
                    multiply.setEnabled(false);
                    division.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    // change the color of the main operation button
                    minus.setBackgroundResource(R.drawable.when_pressed);
                    plus.setBackgroundResource(R.drawable.round_button2);
                    multiply.setBackgroundResource(R.drawable.round_button2);
                    division.setBackgroundResource(R.drawable.round_button2);

                    if(equationFlag == 0) { // if equation is not continue
                        operation = 2;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{ // if equation is continue
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
                        // only show first 10 digits
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
                    // disable all the button except the main operation button
                    plus.setEnabled(false);
                    minus.setEnabled(false);
                    division.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    // change the color of the main operation button
                    multiply.setBackgroundResource(R.drawable.when_pressed);
                    plus.setBackgroundResource(R.drawable.round_button2);
                    minus.setBackgroundResource(R.drawable.round_button2);
                    division.setBackgroundResource(R.drawable.round_button2);

                    if(equationFlag == 0) { // if equation is not continue
                        operation = 3;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{ // if equation is continue
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
                        // only show first 10 digits
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
                    // disable all the button except the main operation button
                    plus.setEnabled(false);
                    minus.setEnabled(false);
                    multiply.setEnabled(false);
                    equals.setEnabled(false);
                    sqrt.setEnabled(false);
                    sign.setEnabled(false);
                    // change the color of the main operation button
                    division.setBackgroundResource(R.drawable.when_pressed);
                    plus.setBackgroundResource(R.drawable.round_button2);
                    minus.setBackgroundResource(R.drawable.round_button2);
                    multiply.setBackgroundResource(R.drawable.round_button2);

                    if(equationFlag == 0) { // if equation is not continue
                        operation = 4;
                        op1 = Double.parseDouble(screen.getText().toString());
                        opbackup = op1;
                        screen.setText("");
                        equationFlag = 1;
                    }
                    else{ // if equation is continue
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
                        // only show first 10 digits
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

        // square root
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1) {
                    op1 = Double.parseDouble(screen.getText().toString());
                    result = Math.sqrt(op1);
                    // only show first 10 digits
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

        // only clear temporary flags
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textFlag = 1;
                startFlag = 1;
                screen.setText("0");
            }
        });

        // =
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textFlag != 1) {

                    if(equalsFlag == 0)
                        op2 = Double.parseDouble(screen.getText().toString());

                    // get the operand 2 and then detect the type of operation
                    if (operation == 1)
                        result = opbackup + op2;
                    else if (operation == 2)
                        result = opbackup - op2;
                    else if (operation == 3)
                        result = opbackup * op2;
                    else if (operation == 4)
                        result = opbackup / op2;

                    opbackup = result;

                    // only show first 10 digits
                    if(result.toString().length() > 10)
                        screen.setText(result.toString().substring(0,10));
                    else
                        screen.setText(result.toString());


                    // enable all the buttons
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

                    // reset flags
                    textFlag = 0;
                    equationFlag = 0;
                    startFlag = 1;
                    equalsFlag = 1;
                }
                else{
                    Toast.makeText(MainActivity.this,"Please enter an operand first" , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    // number keys
    public void updateDisplay(View view){

        equalsFlag = 0;

        // enable buttons
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

        // if new operation, then clear the keyboard contents
        if(textFlag == 1 || startFlag == 1){
            screen.setText("");
        }
        // write the number on the screen
        screen.setText(screen.getText().toString() + ((Button)view).getText());
        textFlag = 0;
        startFlag = 0;
    }

}
