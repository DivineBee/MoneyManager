package com.beatrix.debug.views

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.beatrix.debug.databinding.ActivityExchangeBinding

import com.beatrix.debug.models.ExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
/**
 * This class is responsible for the exchange activity
 * using both coroutines and injections from the AppModule class
 */
@AndroidEntryPoint // for dependency injection
class ExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeBinding

    // get the viewModel
    private val viewModel: ExchangeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // when click on the button call conversion
        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString(),
            )
        }

        // collect events from state flow
        lifecycleScope.launchWhenStarted {
            // get all events
            viewModel.conversion.collect { event ->
                when (event) {
                    // when it's successful then then
                    is ExchangeViewModel.CurrencyEvent.Success -> {
                        // make progressBar invisible
                        binding.progressBar.isVisible = false
                        // set result in color black
                        binding.tvResult.setTextColor(Color.BLACK)
                        // show result text
                        binding.tvResult.text = event.resultText
                    }
                    is ExchangeViewModel.CurrencyEvent.Failure -> {
                        // make progressBar invisible
                        binding.progressBar.isVisible = false
                        // set result in color red
                        binding.tvResult.setTextColor(Color.RED)
                        // show result text
                        binding.tvResult.text = event.errorText
                    }
                    is ExchangeViewModel.CurrencyEvent.Loading -> {
                        // make progressBar visible
                        binding.progressBar.isVisible = true
                    }
                    // in case its empty do nothing
                    else -> Unit
                }
            }
        }
    }
}