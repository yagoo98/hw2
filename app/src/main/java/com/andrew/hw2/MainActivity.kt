package com.andrew.hw2

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.andrew.hw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    fun calculate(view: View) {
        val height = binding.heightInput.text.toString().ifBlank { "0.0" }.toDouble()
        val weight = binding.weightInput.text.toString().ifBlank { "0.0" }.toDouble()

        val bmiCalculation = BmiCalculation()
        val title = when (bmiCalculation.getTitle(height, weight)) {
            BmiCalculation.WeightRank.UNDERWEIGHT -> getString(R.string.underweight)
            BmiCalculation.WeightRank.HEALTHY -> getString(R.string.healthy)
            BmiCalculation.WeightRank.OVERWEIGHT -> getString(R.string.overweight)
            BmiCalculation.WeightRank.OBESE -> getString(R.string.obese)
            BmiCalculation.WeightRank.EXOBESE -> getString(R.string.extremely_obese)
            BmiCalculation.WeightRank.WARNING -> getString(R.string.error_title)
        }
        val message = when (bmiCalculation.getMessage(height, weight)) {
            BmiCalculation.Message.WEIGHT -> getString(R.string.please_enter_your_weight)
            BmiCalculation.Message.HEIGHT -> getString(R.string.please_enter_your_height)
            BmiCalculation.Message.ALL -> getString(R.string.please_enter_your_height_and_weight)
            BmiCalculation.Message.NORMAL -> "bmi= ${bmiCalculation.getBmi(height, weight)} ${
                getString(R.string.bmi_unit)
            }"
        }

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                if (height * weight > 0) {
                    binding.heightInput.setText(getString(R.string.empty))
                    binding.weightInput.setText(getString(R.string.empty))
                }
            }
            .setNegativeButton("cancel", null)
            .show()
    }
}