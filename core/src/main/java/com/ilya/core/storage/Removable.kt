package com.ilya.core.storage

interface Removable<in T> {
    suspend fun remove(willRemove: T): Result<Unit>
}