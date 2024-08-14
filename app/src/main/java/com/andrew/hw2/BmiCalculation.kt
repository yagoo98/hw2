package com.andrew.hw2

import kotlin.math.round

class BmiCalculation {
    enum class WeightRank {
        UNDERWEIGHT, HEALTHY, OVERWEIGHT, OBESE, EXOBESE, WARNING
    }

    enum class Message {
        WEIGHT, HEIGHT, ALL, NORMAL
    }

    fun getBmi(height: Double, weight: Double): Double {
        return round(weight / (height * height) * 100) / 100
    }

    private fun getWeightRank(bmi: Double): WeightRank {
        return when {
            0.0 < bmi && bmi < 18.5 -> WeightRank.UNDERWEIGHT
            18.5 <= bmi && bmi < 24.0 -> WeightRank.HEALTHY
            24.0 <= bmi && bmi < 30.0 -> WeightRank.OVERWEIGHT
            30.0 <= bmi && bmi < 35.0 -> WeightRank.OBESE
            35.0 <= bmi && bmi != Double.POSITIVE_INFINITY -> WeightRank.EXOBESE
            else -> WeightRank.WARNING
        }
    }

    fun getMessage(height: Double, weight: Double): Message {
        return when {
            height > 0 && weight == 0.0 -> Message.WEIGHT
            height == 0.0 && weight > 0 -> Message.HEIGHT
            height == 0.0 && weight == 0.0 -> Message.ALL
            else -> Message.NORMAL
        }
    }

    fun getTitle(height: Double, weight: Double): WeightRank {
        val bmi = getBmi(height, weight)

        return getWeightRank(bmi)
    }
}