package com.codelabs.spotifyclone.common.domain

interface ExceptionHandler {
    fun handle(throwable: Throwable): ExceptionCause
}