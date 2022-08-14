package com.devkey.wheaterapp.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkey.wheaterapp.R
import com.devkey.wheaterapp.common.entities.WeatherForecastEntity
import com.devkey.wheaterapp.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = MainRepository()

    private val result = MutableLiveData<WeatherForecastEntity>()
    fun getResult(): LiveData<WeatherForecastEntity> = result

    private val snackbarMessage = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMessage

    private val loaded = MutableLiveData<Boolean>()
    fun isLoad() = loaded

    suspend fun getWeatherForecast(lat: Double, lon: Double,appId: String, units: String,
                                   lang: String){
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultServer = repository.getWeatherAndForecast(lat, lon, appId, units, lang)
                result.value = resultServer
            }catch (e: Exception){
                snackbarMessage.value = R.string.main_error_server
                e.printStackTrace()
            }finally {
                loaded.value = true
            }
        }
    }

}