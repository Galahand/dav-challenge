package com.example.platzi_challenge.data

sealed class Response<T>{
    class Success<T>(val data: T) : Response<T>()
    class Error<T>(val throwable: Throwable) : Response<T>()
}
