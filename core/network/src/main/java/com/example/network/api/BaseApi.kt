package com.example.network.api

import retrofit2.Response

object BaseApi {
    suspend fun <T : Any> execute(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                if (response.code() == 403) {
                    println("Authentication Failed")
                }
                Result.Error(
                    ApiError(
                        ApiErrorTypes.UNAUTHORIZED,
                        response.errorBody()?.string() ?: "Something went wrong"
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(ApiError(ApiErrorTypes.SERVER_ERROR, e.message ?: "connection error"))
        }
    }
}