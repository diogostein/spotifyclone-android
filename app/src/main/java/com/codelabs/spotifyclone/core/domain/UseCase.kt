package com.codelabs.spotifyclone.core.domain

import kotlinx.coroutines.flow.Flow

interface UseCase<Input, Output> {
    operator fun invoke(input: Input): Flow<Output>

    object Empty
}