package com.moxit.calculator;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity implements TextView.OnClickListener, TextView.OnLongClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Button number0 = findViewById(R.id.button_0);
        Button number1 = findViewById(R.id.button_1);
        Button number2 = findViewById(R.id.button_2);
        Button number3 = findViewById(R.id.button_3);
        Button number4 = findViewById(R.id.button_4);
        Button number5 = findViewById(R.id.button_5);
        Button number6 = findViewById(R.id.button_6);
        Button number7 = findViewById(R.id.button_7);
        Button number8 = findViewById(R.id.button_8);
        Button number9 = findViewById(R.id.button_9);
        Button decimalDot = findViewById(R.id.button_decimal);
        Button add = findViewById(R.id.button_add);
        Button subtract = findViewById(R.id.button_sub);
        Button multiply = findViewById(R.id.button_multiply);
        Button divide = findViewById(R.id.button_divide);
        Button percent = findViewById(R.id.button_percent);
        Button allclear = findViewById(R.id.clear);
        Button clear = findViewById(R.id.bClear);
        Button equals = findViewById(R.id.button_equals);


        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        decimalDot.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        percent.setOnClickListener(this);
        allclear.setOnClickListener(this);
        clear.setOnClickListener(this);
        equals.setOnClickListener(this);

        clear.setOnLongClickListener(this);

    }



    @Override
    public void onClick(View v) {

        int currentID = v.getId();
        TextView currentDisplay=findViewById(currentID);
        String currentNum = currentDisplay.getText().toString();

        TextView calcArea = findViewById(R.id.tvCalc);
        TextView calcExpression = findViewById(R.id.tvExpression);
        String currentCalculator = calcArea.getText().toString();
        String currentExpression = calcExpression.getText().toString();


        if (currentID == R.id.bClear){
            try{
                int calculatorLength = currentCalculator.length();
                currentCalculator = currentCalculator.substring(0,calculatorLength-1);
                calcArea.setText(currentCalculator);
                currentExpression=currentCalculator;
                calcExpression.setText(currentExpression);
            }catch (Exception e){
            }
        }
        else if(currentID == R.id.button_equals){
            if(currentCalculator.contains("+")) {
                calculateResult("add",currentNum,currentCalculator, currentExpression,calcArea,calcExpression);
            }
            else if (currentCalculator.contains("-")) {
                calculateResult("sub",currentNum,currentCalculator, currentExpression,calcArea,calcExpression);
            }
            else if (currentCalculator.contains("x")) {
                calculateResult("mul",currentNum,currentCalculator, currentExpression,calcArea,calcExpression);
            }
            else if (currentCalculator.contains("/")) {
                calculateResult("div",currentNum,currentCalculator, currentExpression,calcArea,calcExpression);
            }
            else if (currentCalculator.contains("%")){
                calculateResult("percent",currentNum,currentCalculator, currentExpression,calcArea,calcExpression);
            }
            calcExpression.setText(currentCalculator);
            calcArea.setTextSize(36);
        }

        else if(currentCalculator.length()<currentExpression.length()){
            calcArea.setTextSize(30);
            currentExpression=currentNum;
            currentCalculator=currentNum;
            calcArea.setText(currentCalculator);
            calcExpression.setText(currentExpression);
        }
        else if(currentID == R.id.clear){
            calcArea.setText("");
            calcExpression.setText("");
        }
        else {
            currentExpression+=currentNum;
            currentCalculator+=currentNum;
            calcArea.setText(currentCalculator);
            calcExpression.setText(currentExpression);
        }

    }

    @Override
    public boolean onLongClick(View v) {
        TextView calcArea = findViewById(R.id.tvCalc);
        TextView calcExpression = findViewById(R.id.tvExpression);
        calcArea.setText("");
        calcExpression.setText("");
        calcArea.setTextSize(30);
        return true;
    }

    private void calculateResult(String operation, String currentNum,String currentCalculator,String currentExpression, TextView calcArea,TextView calcExpression){
        String strResult="";
        String[] variables;
        switch (operation){
            case "add":
                variables = currentCalculator.split("\\+",2);//escape sequence used for + sign
                strResult=String.valueOf(Float.parseFloat(variables[0]) + Float.parseFloat(variables[1]));
                break;
            case "sub":
                variables = currentCalculator.split("-",2);
                strResult=String.valueOf(Float.parseFloat(variables[0]) - Float.parseFloat(variables[1]));
                break;
            case "mul":
                variables = currentCalculator.split("x",2);
                strResult=String.valueOf(Float.parseFloat(variables[0]) * Float.parseFloat(variables[1]));
                break;
            case "div":
                variables = currentCalculator.split("/",2);
                strResult=String.valueOf(Float.parseFloat(variables[0]) / Float.parseFloat(variables[1]));
                break;
            case "percent":
                variables = currentCalculator.split("%",2);
                double amount = Float.parseFloat(variables[0]);
                double res = (amount / 100.0f) * 10;
                strResult= String.valueOf(res);
                break;

        }
        System.out.println("strResult "+strResult);

        String decimalSplit[] = strResult.split(".",2);
        if(strResult.contains(".0") || decimalSplit[1] == ".0"){
            currentCalculator=strResult.replace(".0","");
        }
        else{
            currentCalculator=strResult;
        }
        calcArea.setText(currentCalculator);
        calcExpression.setText(currentExpression);


    }
    
}

