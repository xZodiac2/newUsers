package com.ilya.core.useCase

interface OrdinaryUseCase<out T> {
    operator fun invoke(data: Any): Result<T>
}