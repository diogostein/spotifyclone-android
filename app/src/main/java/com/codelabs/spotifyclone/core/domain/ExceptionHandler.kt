package com.codelabs.spotifyclone.core.domain

interface ExceptionHandler {
    fun handle(throwable: Throwable): ExceptionCause
}