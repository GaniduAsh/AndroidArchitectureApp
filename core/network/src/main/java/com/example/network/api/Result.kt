package com.example.network.api

sealed class Result<T>(
    val data: T? = null,
    val error: ApiError? = null
) {
    class Success<T>(data: T) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(error: ApiError, data: T? = null) :
        Result<T>(data, error)
}
