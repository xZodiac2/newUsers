package com.ilya.usersupgrade

import android.content.Context

fun Error.extract(context: Context): String {
    return context.getString(stringId)
}