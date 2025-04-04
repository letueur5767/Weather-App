package me.tangobee.weathernaut.utils

import java.math.BigDecimal
import java.math.RoundingMode

class UnitConverter {

    fun convertTemperature(value: String, fromUnit: String, toUnit: String): Double {
        val parsedValue = parseDoubleSafe(value)
        val result = when (fromUnit to toUnit) {
            "celsius" to "fahrenheit" -> (parsedValue * 9 / 5) + 32
            "fahrenheit" to "celsius" -> (parsedValue - 32) * 5 / 9
            else -> parsedValue
        }
        return result.formatToTwoDecimalPlaces()
    }

    fun convertWindSpeed(value: String, fromUnit: String, toUnit: String): Double {
        val parsedValue = parseDoubleSafe(value)
        val result = when (fromUnit to toUnit) {
            "kmh" to "mph" -> parsedValue / 1.60934
            "mph" to "kmh" -> parsedValue * 1.60934
            "kmh" to "ms" -> parsedValue / 3.6
            "ms" to "kmh" -> parsedValue * 3.6
            "mph" to "ms" -> parsedValue * 0.44704
            "ms" to "mph" -> parsedValue / 0.44704
            else -> parsedValue
        }
        return result.formatToTwoDecimalPlaces()
    }

    fun convertPressure(value: String, fromUnit: String, toUnit: String): Double {
        val parsedValue = parseDoubleSafe(value)
        val result = when (fromUnit to toUnit) {
            "hpa" to "atm" -> parsedValue / 1013.25
            "atm" to "hpa" -> parsedValue * 1013.25
            else -> parsedValue
        }
        return result.formatToTwoDecimalPlaces()
    }

    // ✅ Fonction pour convertir un String en Double en remplaçant "," par "."
    private fun parseDoubleSafe(value: String): Double {
        return value.replace(",", ".").toDoubleOrNull() ?: 0.0
    }

    // ✅ Utilisation de BigDecimal pour éviter les erreurs de précision
    private fun Double.formatToTwoDecimalPlaces(): Double {
        return BigDecimal(this).setScale(2, RoundingMode.HALF_UP).toDouble()
    }
}
