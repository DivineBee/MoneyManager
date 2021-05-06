package com.beatrix.debug.utils

import com.beatrix.debug.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// after api change I had to register and get an API key
const val API_KEY = "dd611a69b0c59566e325de46673a1c23"

/**
 * This class was initially bigger but because there were changes
 * of used API policy, I had to remove some requests which allowed
 * to do and get/post more stuff like all currencies converter, history,
 * all rates for today and so on. No it's available only getting information
 * for EUR rate in comparison with other available currencies. Anyway I could
 * make some changes with incoming data in the way that I calculate via a formula
 * the currency for EUR. ex: I pass 20 eur and I get 23.54 usd, instead of just
 * looking at how much usd(or any other currency) is 1 eur.
 */
interface CurrencyApi {
    // get request for api
    @GET("/v1/latest?access_key=dd611a69b0c59566e325de46673a1c23")
    // get the base rate of type String
    suspend fun getRates(
        @Query("base") base: String
    // get response from retrofit of type CurrencyResponse
    ): Response<CurrencyResponse>

}