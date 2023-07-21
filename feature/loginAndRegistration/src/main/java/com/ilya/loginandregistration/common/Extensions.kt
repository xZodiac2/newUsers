package com.ilya.loginandregistration.common

import android.content.Context

internal fun Error.extract(context: Context): String {
    return context.getString(stringId)
}