package com.beatrix.debug.utils

// Generic class for seeing if we get errors in case we don't have internet connection
sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
}