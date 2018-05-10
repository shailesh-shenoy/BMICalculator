package com.beproductive.bmicalculator;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private RadioGroup radioGender;
    private RadioButton radioSelectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all views
        final TextView bmiTextBox = findViewById(R.id.BMI_text);
        final EditText inputHeightBox = findViewById(R.id.input_height_box);
        final EditText inputWeightBox = findViewById(R.id.input_weight_box);
        radioGender = findViewById(R.id.radioGender);
        Button calculateButton = findViewById(R.id.calculateButton);

        // Calculate Button Click behaviour
        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get selected radio button id
                int selectedRadioId = radioGender.getCheckedRadioButtonId();
                radioSelectedGender = findViewById(selectedRadioId);
                String gender = radioSelectedGender.getText().toString();

                // Get height and weight from EditText boxes
                double height = Double.parseDouble(inputHeightBox.getText().toString());
                double weight = Double.parseDouble(inputWeightBox.getText().toString());

                // Calculate BMI and set text in BMI textbox
                double bmi = calculateBMI(height, weight);
                bmiTextBox.setText(String.format(Locale.ENGLISH, "BMI: %.2f", bmi));
                String healthStatus = getHealthStatus(bmi, gender);

                if(healthStatus.equals("Underweight"))
                    bmiTextBox.setTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.colorUnderweight));

                else if(healthStatus.equals("Healthy"))
                    bmiTextBox.setTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.colorHealthy));

                else if(healthStatus.equals("Overweight"))
                    bmiTextBox.setTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.colorOverweight));

                else if(healthStatus.equals("Obese"))
                    bmiTextBox.setTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.colorObese));

                Log.d("BMI Calculator", "Height: " + height +
                        " Weight: " + weight + " BMI: " + bmi + " Gender: " + gender);

            }
        });

    }

    private static String getHealthStatus(double bmi, String gender)
    {
        if(gender.equals("Male"))
        {
            if(bmi <= 18.0)
                return "Underweight";
            else if(bmi > 18.0 && bmi <= 25)
                return "Healthy";
            else if(bmi > 25 && bmi < 30)
                return "Overweight";
            else
                return "Obese";
        }
        else if(gender.equals("Female"))
        {
            if(bmi <= 20.0)
                return "Underweight";
            else if(bmi > 20.0 && bmi <= 27)
                return "Healthy";
            else if(bmi > 27 && bmi < 32)
                return "Overweight";
            else
                return "Obese";
        }
        return "Healthy";
    }

    private static double calculateBMI(double height, double weight)
    {
        return weight/(height * height);
    }
}
