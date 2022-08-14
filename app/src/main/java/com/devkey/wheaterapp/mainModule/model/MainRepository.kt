package com.devkey.wheaterapp.mainModule.model

import com.devkey.wheaterapp.common.entities.WeatherForecastEntity

class MainRepository {

    private val remoteDatabase = RemoteDatabase()

    suspend fun getWeatherAndForecast(lat: Double, lon: Double,appId: String, units: String,
                                      lang: String) : WeatherForecastEntity =
        remoteDatabase.getWeatherForecastByCoordinates(lat, lon, appId, units, lang)

}