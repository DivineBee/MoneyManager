package com.beatrix.debug.models

// This class contains entries from the api's json
data class CurrencyResponse(
    val base: String,
    val date: String,
    // the rates are defined in separate class of type Rates
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)