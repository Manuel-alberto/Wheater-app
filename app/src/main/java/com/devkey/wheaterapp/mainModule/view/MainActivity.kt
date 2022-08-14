package com.devkey.wheaterapp.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devkey.wheaterapp.BR
import com.devkey.wheaterapp.R
import com.devkey.wheaterapp.common.entities.Forecast
import com.devkey.wheaterapp.common.utils.CommonUtils
import com.devkey.wheaterapp.databinding.ActivityMainBinding
import com.devkey.wheaterapp.mainModule.view.adapters.ForecastAdapter
import com.devkey.wheaterapp.mainModule.view.adapters.OnClickListener
import com.devkey.wheaterapp.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() ,OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ForecastAdapter


    //c45c3984c3b5adbcbef09099b474c6a8 6a5c325c9265883997730d09be2328e8
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
        setupAdapter()
        setupRecyclerView()

    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.getSnackbarMsg().observe(this){resMsg->
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_SHORT).show()
            }
            it.getResult().observe(this){result->
                adapter.submitList(result.hourly)
            }
        }
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupAdapter(){
        adapter = ForecastAdapter(this)
    }

    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            binding.viewModel?.getWeatherForecast(19.4109, -99.1260
                , "c45c3984c3b5adbcbef09099b474c6a8", "metric", "en")
        }
    }

    //OnClickListener
    override fun onClick(forecast: Forecast) {
        Snackbar.make(binding.root, CommonUtils.getFullDate(forecast.dt), Snackbar.LENGTH_SHORT).show()
    }

}