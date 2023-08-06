package com.ilya.core

import android.content.Context
import com.ilya.core.abstractClasses.UsersApplicationError


fun <K, V> Map<K, V>.findFirstOrNull(conditionOfFinding: (V) -> Boolean): V? {
    var searchable: V? = null
    
    forEach {(_, V) ->
        if (conditionOfFinding(V)) {
            searchable = V
            return@forEach
        }
    }
    
    return searchable
}

fun UsersApplicationError.extract(context: Context): String {
    return context.getString(stringId)
}