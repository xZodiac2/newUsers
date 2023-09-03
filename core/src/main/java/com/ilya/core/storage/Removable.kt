package com.ilya.core.storage

interface Removable<in T> {
    fun remove(willRemove: T): Result<Unit>
}