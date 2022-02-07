package com.codelabs.spotifyclone.common.data

import com.codelabs.spotifyclone.common.domain.ExceptionCause
import com.codelabs.spotifyclone.common.domain.ExceptionHandler
import retrofit2.HttpException
import java.io.IOException

class DataExceptionHandlerImpl : ExceptionHandler {

    override fun handle(throwable: Throwable): ExceptionCause {
        return when (throwable) {
            is IOException -> ExceptionCause.NoConnection
            is HttpException -> ExceptionCause.NetworkFailure(throwable.message)
            else -> ExceptionCause.Unknown
        }
    }

}