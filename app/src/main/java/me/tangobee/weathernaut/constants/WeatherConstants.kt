package me.tangobee.weathernaut.constants

data class WeatherConstants(
    val code: Int,
    val description: String
)

object WeatherCodes {
    val weatherConstants = listOf(
        WeatherConstants(0, "Clair"),
        WeatherConstants(1, "Clair / Partiellement nuageux"),
        WeatherConstants(2, "Partiellement nuageux"),
        WeatherConstants(3, "Couvert"),
        WeatherConstants(45, "Brouillard"),
        WeatherConstants(48, "Brouillard givrant"),
        WeatherConstants(51, "Bruine légère"),
        WeatherConstants(53, "Bruine modérée"),
        WeatherConstants(55, "Bruine forte"),
        WeatherConstants(56, "Bruine verglaçante légère"),
        WeatherConstants(57, "Bruine verglaçante forte"),
        WeatherConstants(61, "Pluie légère"),
        WeatherConstants(63, "Pluie modérée"),
        WeatherConstants(65, "Pluie forte"),
        WeatherConstants(66, "Pluie verglaçante légère"),
        WeatherConstants(67, "Pluie verglaçante forte"),
        WeatherConstants(71, "Neige légère"),
        WeatherConstants(73, "Neige modérée"),
        WeatherConstants(75, "Neige forte"),
        WeatherConstants(77, "Grains de neige"),
        WeatherConstants(80, "Averses de pluie légères"),
        WeatherConstants(81, "Averses de pluie modérées"),
        WeatherConstants(82, "Averses de pluie fortes"),
        WeatherConstants(85, "Averses de neige légères"),
        WeatherConstants(86, "Averses de neige fortes"),
        WeatherConstants(95, "Orage faible"),
        WeatherConstants(96, "Orage faible"),
        WeatherConstants(99, "Orage fort")

    )
}
