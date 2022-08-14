package com.devkey.wheaterapp.common.utils

import com.devkey.wheaterapp.common.entities.Weather
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    fun getHour(epoch: Long): String = getFormatedTime(epoch, "HH:mm")
    fun getFullDate(epoch: Long): String = getFormatedTime(epoch, "dd/MM/yy HH:mm")

    private fun getFormatedTime(epoch: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(epoch*1000)
    }

    fun getWeather(weather: List<Weather>?): String{
        return if (weather != null && weather.isNotEmpty()) weather[0].main else "-"
    }

    fun getDescription(weather: List<Weather>?): String{
        return if (weather != null && weather.isNotEmpty()) weather[0].description else "-"
    }

}