package com.beatrix.debug.di

import com.beatrix.debug.utils.CurrencyApi
import com.beatrix.debug.repo.DefaultExchangeRepository
import com.beatrix.debug.repo.ExchangeRepository
import com.beatrix.debug.utils.Constants.Companion.BASE_URL
import com.beatrix.debug.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
// This tells that the dependencies live as long as application lives
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton // means that we have one instance throughout the app
    @Provides // provide instance so we can inject it
    fun provideCurrencyApi(): CurrencyApi = Retrofit.Builder()
        // base url defined in the constants (from which url we get data)
        .baseUrl(BASE_URL)
        // responsible for converting json response to data classes
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        // create the api and pass the CurrencyApi
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides // provides main repository which takes the api
    fun provideMainRepostiory(api: CurrencyApi): ExchangeRepository = DefaultExchangeRepository(api)

    @Singleton
    @Provides // provides the dispatchers
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}