package com.example.fitness

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity

class BMI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        // Initialize the input fields, button, and result TextView
        val heightInput = findViewById<TextInputEditText>(R.id.height_input)
        val weightInput = findViewById<TextInputEditText>(R.id.weight_input)
        val resultText = findViewById<TextView>(R.id.result_txt)
        val buttonCalculate = findViewById<Button>(R.id.button_calculate)

        // Calculate the BMI when the button is clicked
        buttonCalculate.setOnClickListener {
            // Retrieve the height and weight values from the input fields
            val height = heightInput.text.toString().toDoubleOrNull()
            val weight = weightInput.text.toString().toDoubleOrNull()

            // Check if the inputs are valid numbers
            if (height != null && weight != null) {
                // Calculate BMI (BMI = weight (kg) / height (m)^2)
                val bmi = calculateBMI(weight, height)

                // Update the result TextView to show the calculated BMI
                resultText.text = "Your BMI: %.2f".format(bmi)
            } else {
                // If inputs are invalid, display an error message
                resultText.text = "Please enter valid values."
            }
        }
    }

    // Function to calculate BMI based on weight (kg) and height (cm)
    private fun calculateBMI(weight: Double, height: Double): Double {
        // Convert height from cm to meters
        val heightInMeters = height / 100

        // Calculate BMI
        return weight / (heightInMeters * heightInMeters)
    }
}
