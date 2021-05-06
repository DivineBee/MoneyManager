package com.beatrix.debug.utils

import kotlinx.coroutines.CoroutineDispatcher

// make sure that the view model choose the test dispatcher
// for all test cases and normal dispatchers for real app
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}