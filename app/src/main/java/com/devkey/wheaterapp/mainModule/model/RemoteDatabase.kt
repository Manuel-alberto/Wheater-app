package com.devkey.wheaterapp.mainModule.model

import com.devkey.wheaterapp.common.dataAccess.WeatherService
import com.devkey.wheaterapp.common.entities.WeatherForecastEntity
import com.devkey.wheaterapp.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)

    suspend fun getWeatherForecastByCoordinates
                (lat: Double, lon: Double,appId: String, units: String, lang: String)
                :WeatherForecastEntity = withContext(Dispatchers.IO){

        service.getWeatherForecastByCoordinates(lat, lon, appId, units, lang)

    }
}