package com.ilya.core.useCase

interface CoroutineUseCase<out T> {
    suspend fun execute(data: Any): Result<T>
}