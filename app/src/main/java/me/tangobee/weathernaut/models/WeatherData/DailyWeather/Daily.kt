package me.tangobee.weathernaut.models.WeatherData.DailyWeather

data class Daily(
    val sunrise: List<String>,
    val sunset: List<String>,
    var temperature_2m_max: List<Double>,
    var temperature_2m_min: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>
)