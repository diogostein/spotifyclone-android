package com.codelabs.spotifyclone.common.domain

import kotlinx.coroutines.flow.Flow

interface UseCase<Input, Output> {
    fun execute(input: Input? = null): Flow<Output>
}