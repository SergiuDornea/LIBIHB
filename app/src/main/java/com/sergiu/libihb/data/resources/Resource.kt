package com.sergiu.libihb.data.resources

import java.lang.Exception

sealed class Resource<out T>{
    // loading state
    object Loading: Resource<Nothing>()

    data class Success<out T>(
        val data: T
    ): Resource<T>()

    data class Failure(
        val exception: Exception
    ): Resource<Nothing>()
}
