package com.ilya.core.storage

interface Addable<in T> {
    fun add(data: T): Result<Unit>
}