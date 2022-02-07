package com.codelabs.spotifyclone.common

import com.codelabs.spotifyclone.common.domain.ExceptionHandler
import java.lang.Exception

suspend fun <T> repositoryResultHandler(
    exceptionHandler: ExceptionHandler,
    callback: suspend () -> Result<Nothing, T>
): Result<Nothing, T> {
    return try {
        callback.invoke()
    } catch (e: Exception) {
        Result.Exception(exceptionHandler.handle(e))
    }
}