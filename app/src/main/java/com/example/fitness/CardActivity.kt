package com.example.fitness

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CardActivity : AppCompatActivity() {
    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var etAge: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var tvResult: TextView
    private lateinit var btnCalculateBMI: Button
    private lateinit var btnCalculateBMR: Button
    private lateinit var btnCalculateBodyFat: Button
    private lateinit var tvAppTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        // Initialize views (fixed)
        etHeight = findViewById(R.id.etHeight)
        etWeight = findViewById(R.id.etWeight)
        etAge = findViewById(R.id.etAge)
        rgGender = findViewById(R.id.rgGender)
        tvResult = findViewById(R.id.tvResult)
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI)
        btnCalculateBMR = findViewById(R.id.btnCalculateBMR)
        btnCalculateBodyFat = findViewById(R.id.btnCalculateBodyFat)
        tvAppTitle = findViewById(R.id.tvAppTitle)

        // Set app title
        tvAppTitle.text = "For Healthy You"

        // Set button click listeners
        btnCalculateBMI.setOnClickListener {
            calculateBMI()
        }

        btnCalculateBMR.setOnClickListener {
            calculateBMR()
        }

        btnCalculateBodyFat.setOnClickListener {
            calculateBodyFat()
        }
    }

    private fun showResultDialog(title: String, message: String) {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun calculateBMI() {
        try {
            if (validateInputs()) {
                val height = etHeight.text.toString().toFloat() / 100 // Convert cm to m
                val weight = etWeight.text.toString().toFloat()
                val bmi = weight / (height * height)

                val category = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 25 -> "Normal weight"
                    bmi < 30 -> "Overweight"
                    else -> "Obese"
                }

                //tvResult.text = "BMI: %.2f\nCategory: %s".format(bmi, category)
                val resultText = "BMI: %.2f\nCategory: %s".format(bmi, category)
                showResultDialog("BMI Result", resultText)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error calculating BMI", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateBMR() {
        try {
            if (validateInputs()) {
                val height = etHeight.text.toString().toFloat() // in cm
                val weight = etWeight.text.toString().toFloat() // in kg
                val age = etAge.text.toString().toInt()
                val isMale = findViewById<RadioButton>(rgGender.checkedRadioButtonId).text.toString() == "Male"

                // Calculate BMR using Mifflin-St Jeor Equation
                val bmr = if (isMale) {
                    (10 * weight) + (6.25 * height) - (5 * age) + 5
                } else {
                    (10 * weight) + (6.25 * height) - (5 * age) - 161
                }

                //tvResult.text = "BMR: %.2f calories/day".format(bmr)
                val resultText = "BMR: %.2f calories/day".format(bmr)
                showResultDialog("BMR Result", resultText)

            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error calculating BMR", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateBodyFat() {
        try {
            if (validateInputs()) {
                val height = etHeight.text.toString().toFloat() // in cm
                val weight = etWeight.text.toString().toFloat() // in kg
                val age = etAge.text.toString().toInt()
                val isMale = findViewById<RadioButton>(rgGender.checkedRadioButtonId).text.toString() == "Male"

                // Calculate BMI first
                val bmi = weight / ((height / 100) * (height / 100))

                // Calculate body fat using the Deurenberg formula
                val bodyFat = if (isMale) {
                    (1.20 * bmi) + (0.23 * age) - (10.8 * 1) - 5.4
                } else {
                    (1.20 * bmi) + (0.23 * age) - (10.8 * 0) - 5.4
                }

                //tvResult.text = "Estimated Body Fat: %.1f%%".format(bodyFat)
                val resultText = "Estimated Body Fat: %.1f%%".format(bodyFat)
                showResultDialog("Body Fat %", resultText)

            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error calculating body fat", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInputs(): Boolean {
        if (etHeight.text.isNullOrEmpty() || etWeight.text.isNullOrEmpty() || etAge.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
