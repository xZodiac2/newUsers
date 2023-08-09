package com.ilya.core

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed interface TextReference {
    
    data class Resource(
        @StringRes val stringId: Int,
        val formatArgs: List<Any> = emptyList()
    ) : TextReference
    
    data class PluralResource(
        @PluralsRes val id: Int,
        val count: Int,
        val formatArgs: List<Any> = emptyList()
    ) : TextReference
    
    data class Str(val value: String) : TextReference
    
}