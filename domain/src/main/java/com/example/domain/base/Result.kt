package com.example.domain.base

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    val isSuccess: Boolean get() = this !is Error

    val isFailed: Boolean get() = this !is Success

    fun copy(data: T): Result<T> = when (this) {
        is Error -> this
        is Success -> Success(data)
    }

    class Error<T>(message: String) : Result<T>(message = message)

    class Success<T>(data: T) : Result<T>(data = data, message = null)
}