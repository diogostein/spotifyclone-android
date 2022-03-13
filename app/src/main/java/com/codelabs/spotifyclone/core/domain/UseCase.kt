package com.codelabs.spotifyclone.core.domain

import kotlinx.coroutines.flow.Flow

interface UseCase<Input, Output> {
    fun execute(input: Input): Flow<Output>

    class Empty
}