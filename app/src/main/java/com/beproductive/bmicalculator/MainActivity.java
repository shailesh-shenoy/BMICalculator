package com.beproductive.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all views
        final TextView bmiTextBox = findViewById(R.id.BMI_text);
        final EditText inputHeightBox = findViewById(R.id.input_height_box);
        final EditText inputWeightBox = findViewById(R.id.input_weight_box);
        Button calculateButton = findViewById(R.id.calculateButton);


        // Calculate Button Click behaviour
        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Get height and weight from EditText boxes
                double height = Double.parseDouble(inputHeightBox.getText().toString());
                double weight = Double.parseDouble(inputWeightBox.getText().toString());

                // Calculate BMI and set text in BMI textbox
                double bmi = calculateBMI(height, weight);
                bmiTextBox.setText(String.format("BMI: %.2f", bmi));

                Log.d("BMI Calculator", "Height: " + height +
                        " Weight: " + weight + " BMI: " + bmi);

            }
        });

    }

    public static double calculateBMI(double height, double weight)
    {
        return weight/(height * height);
    }
}
