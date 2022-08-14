package com.devkey.wheaterapp.mainModule.view.adapters

import com.devkey.wheaterapp.common.entities.Forecast

interface OnClickListener {
    fun onClick(forecast: Forecast)
}