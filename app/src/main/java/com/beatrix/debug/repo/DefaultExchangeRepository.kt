package com.beatrix.debug.repo;

import com.beatrix.debug.utils.CurrencyApi
import com.beatrix.debug.models.CurrencyResponse
import com.beatrix.debug.utils.Resource
import javax.inject.Inject

// Makes actual network requests
// Inject constructor from AppModule and inherit default repo
class DefaultExchangeRepository @Inject constructor(
    private val api: CurrencyApi
) : ExchangeRepository {

    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            // save response of the request
            val response = api.getRates(base)
            // save the result of the response
            val result = response.body()
            // check if response was successful
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
