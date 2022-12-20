package com.conorcoker.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //class variables are also called fields
    private TextView resultText;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private Button calculateButton;
    private EditText editTextAge;
    private EditText editTextFeet;
    private EditText editTextInches;
    private EditText editTextWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        radioButtonMale = findViewById(R.id.radio_button_male);
        radioButtonFemale = findViewById(R.id.radio_button_female);

        editTextAge = findViewById(R.id.edit_text_age);
        editTextFeet = findViewById(R.id.edit_text_feet);
        editTextInches = findViewById(R.id.edit_text_inches);
        editTextWeight = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                double bmiResult =calculateBmi();
                String ageText = editTextAge.getText().toString();
                int age =Integer.parseInt(ageText);
                if(age>=18){
                    displayResult(bmiResult);
                }
                else{
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private double calculateBmi() {
       String userFeetText = editTextFeet.getText().toString();
       String userInchesText = editTextInches.getText().toString();
       String userWeightText = editTextWeight.getText().toString();

       //converting the number strings into int variables
       int userFeet = Integer.parseInt(userFeetText);
       int userInches = Integer.parseInt(userInchesText);
       int userWeight = Integer.parseInt(userWeightText);

       int totalInches= (userFeet*12) + userInches;
       // height in meters is the inches multiplied by 0.0254
       double heightInMeters= totalInches* 0.0254;

       return userWeight / (heightInMeters*heightInMeters);
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult=myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            fullResultString = bmiTextResult+" - You are underweight";

        }
        else if(bmi > 25){
            fullResultString = bmiTextResult+" - You are overweight";

        }
        else{
            fullResultString = bmiTextResult+" - You are a healthy weight";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult= myDecimalFormatter.format(bmi);
        String fullResultString;
        if(radioButtonMale.isChecked()){
            fullResultString=bmiTextResult+" - As you are under 18, please consult your doctor for the healthy range for boys ";
        }
        else if(radioButtonFemale.isChecked()){
            fullResultString=bmiTextResult+" - As you are under 18, please consult your doctor for the healthy range for girls ";
        }
        else{
            fullResultString=bmiTextResult+" - As you are under 18, please consult your doctor for the healthy range";
        }
        resultText.setText(fullResultString
        );

    }
}