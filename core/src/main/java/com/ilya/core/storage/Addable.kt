package com.ilya.core.storage

interface Addable<in T> {
    suspend fun add(data: T): Result<Unit>
}