package com.ilya.core

interface UseCase<out T> {
    suspend fun execute(data: Any): Result<T>
}