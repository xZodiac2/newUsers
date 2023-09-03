package com.ilya.core

interface UseCase<T> {
    operator fun invoke(data: Any): Result<T>
}