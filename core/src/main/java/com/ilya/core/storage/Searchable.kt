package com.ilya.core.storage

interface Searchable<out T> {
    fun searchByLogin(login: String): Result<T>
}