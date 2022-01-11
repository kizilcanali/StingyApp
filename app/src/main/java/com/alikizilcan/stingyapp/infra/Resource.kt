package com.alikizilcan.stingyapp.infra

import java.lang.Exception

sealed class Resource<out T>{
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Throwable? = null): Resource<Nothing>()
    object Loading: Resource<Nothing>()
}

inline fun<T, R> Resource<T>.map(transform: (T) -> R): Resource<R>{
    return when(this){
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(exception)
        is Resource.Loading -> Resource.Loading
    }
}

