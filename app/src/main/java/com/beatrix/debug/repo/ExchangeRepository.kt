package com.beatrix.debug.repo

import com.beatrix.debug.models.CurrencyResponse
import com.beatrix.debug.utils.Resource

// For testing purposes
interface ExchangeRepository {
    // with use of Resource class we can check in the viewmodel if the response were successful or not
    suspend fun getRates(base: String): Resource<CurrencyResponse>
}