package com.example.fitness

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log10


class fats: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fats)

        // Initialize views
        val genderGroup = findViewById<RadioGroup>(R.id.gender_group)
        val heightInput = findViewById<TextInputEditText>(R.id.height_input)
        val weightInput = findViewById<TextInputEditText>(R.id.weight_input)
        val neckInput = findViewById<TextInputEditText>(R.id.neck_input)
        val waistInput = findViewById<TextInputEditText>(R.id.waist_input)
        val hipsInput = findViewById<TextInputEditText>(R.id.hips_input)
        val calculateButton = findViewById<Button>(R.id.button_calculate)
        val resultText = findViewById<TextView>(R.id.result_txt)

        // Handle button click for calculation
        calculateButton.setOnClickListener {
            // Get gender selection
            val selectedGenderId = genderGroup.checkedRadioButtonId
            val selectedGender: String = if (selectedGenderId == R.id.radio_male) "male" else "female"

            // Get the input values
            val height = heightInput.text.toString().toDoubleOrNull()
            val weight = weightInput.text.toString().toDoubleOrNull()
            val neck = neckInput.text.toString().toDoubleOrNull()
            val waist = waistInput.text.toString().toDoubleOrNull()
            val hips = if (selectedGender == "female") hipsInput.text.toString().toDoubleOrNull() else null

            if (height != null && weight != null && neck != null && waist != null && (selectedGender == "male" || hips != null)) {
                // Perform body fat calculation based on gender
                val bodyFat = if (selectedGender == "male") {
                    calculateBodyFatForMale(waist, neck, height)
                } else {
                    calculateBodyFatForFemale(waist, hips, neck, height)
                }

                // Display the result
                resultText.text = "Body Fat Percentage: %.2f%%".format(bodyFat)
            } else {
                // Show error if any input is invalid
                Toast.makeText(this, "Please fill in all fields with valid values", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to calculate body fat for male
    private fun calculateBodyFatForMale(waist: Double, neck: Double, height: Double): Double {
        return 86.010 * log10(waist - neck) - 70.041 * log10(height) + 36.76
    }

    // Function to calculate body fat for female
    private fun calculateBodyFatForFemale(waist: Double, hips: Double?, neck: Double, height: Double): Double {
        // Ensure hips is not null before calculation
        if (hips != null) {
            return 163.205 * log10(waist + hips - neck) - 97.684 * log10(height) - 78.387
        } else {
            throw IllegalArgumentException("Hips measurement is required for female body fat calculation.")
        }
    }
}