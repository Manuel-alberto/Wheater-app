package com.devkey.wheaterapp.common.dataAccess

import com.devkey.wheaterapp.common.entities.WeatherForecastEntity
import com.devkey.wheaterapp.common.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(Constants.ONE_CALL_PATH)
    suspend fun getWeatherForecastByCoordinates(
        @Query(Constants.LATITUDE_PARAM) lat: Double,
        @Query(Constants.LONGITUDE_PARAM) lon: Double,
        @Query(Constants.APP_ID_PARAM) appid: String,
        @Query(Constants.UNITS_PARAM) unit: String,
        @Query(Constants.LANGUAGE_PARAM) lang: String
    ): WeatherForecastEntity
}