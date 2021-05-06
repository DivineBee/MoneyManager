package com.beatrix.debug.models

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrix.debug.repo.ExchangeRepository
import com.beatrix.debug.utils.DispatcherProvider
import com.beatrix.debug.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

// Inject viewModel and coroutine Dispatcher
class ExchangeViewModel @ViewModelInject constructor(
    private val repository: ExchangeRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class CurrencyEvent {
        // class for success which takes success text as parameter
        class Success(val resultText: String) : CurrencyEvent()
        // class for failure which takes error text as parameter
        class Failure(val errorText: String) : CurrencyEvent()
        // loading currency event
        object Loading : CurrencyEvent()
        // object for initial state
        object Empty : CurrencyEvent()
    }

    // state flow with empty event by default
    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    // normal state flow
    val conversion: StateFlow<CurrencyEvent> = _conversion

    fun convert(
        amountStr: String, // amount to convert
        fromCurrency: String, // from which currency to convert
        toCurrency: String // to which currency to convert
    ) {
        // convert to float or null in case of exception
        val fromAmount = amountStr.toFloatOrNull()

        if (fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        // launch coroutine in io dispatcher
        viewModelScope.launch(dispatchers.io) {
            // loading value for progress bar
            _conversion.value = CurrencyEvent.Loading

            // expression where get expression rates response by using getRates
            when (val ratesResponse = repository.getRates(fromCurrency)) {
                // if that is an error then display error text
                is Resource.Error -> _conversion.value =
                    // it is wrapped around the failure event
                    CurrencyEvent.Failure(ratesResponse.message!!)
                // if everything went well
                is Resource.Success -> {
                    // get rates from the rates response
                    val rates = ratesResponse.data!!.rates
                    // get actual rate
                    val rate = getRateForCurrency(toCurrency, rates)
                    // in case the rate is null
                    if (rate == null) {
                        // set conversion value to failure
                        _conversion.value = CurrencyEvent.Failure("Unexpected error")
                    } else {
                        // in case its done well then get convertion of currency using round command
                        val convertedCurrency = round(fromAmount * rate * 100) / 100
                        // conversion value equals to success
                        _conversion.value = CurrencyEvent.Success(
                            // convert result to string
                            "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                        )
                    }
                }
            }
        }
    }

    // returns the corresponding value of rates class
    private fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
        "CAD" -> rates.cAD
        "HKD" -> rates.hKD
        "ISK" -> rates.iSK
        "EUR" -> rates.eUR
        "PHP" -> rates.pHP
        "DKK" -> rates.dKK
        "HUF" -> rates.hUF
        "CZK" -> rates.cZK
        "AUD" -> rates.aUD
        "RON" -> rates.rON
        "SEK" -> rates.sEK
        "IDR" -> rates.iDR
        "INR" -> rates.iNR
        "BRL" -> rates.bRL
        "RUB" -> rates.rUB
        "HRK" -> rates.hRK
        "JPY" -> rates.jPY
        "THB" -> rates.tHB
        "CHF" -> rates.cHF
        "SGD" -> rates.sGD
        "PLN" -> rates.pLN
        "BGN" -> rates.bGN
        "CNY" -> rates.cNY
        "NOK" -> rates.nOK
        "NZD" -> rates.nZD
        "ZAR" -> rates.zAR
        "USD" -> rates.uSD
        "MXN" -> rates.mXN
        "ILS" -> rates.iLS
        "GBP" -> rates.gBP
        "KRW" -> rates.kRW
        "MYR" -> rates.mYR
        else -> rates.eUR
    }
}