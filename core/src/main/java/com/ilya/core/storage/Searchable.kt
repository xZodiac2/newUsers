package com.ilya.core.storage

interface Searchable<out T> {
    suspend fun searchByLogin(login: String): Result<T>
}