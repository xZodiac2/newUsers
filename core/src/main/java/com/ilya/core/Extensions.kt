package com.ilya.core

import android.content.Context
import android.view.View
import android.widget.TextView
import com.ilya.core.enums.ViewVisibility
import java.security.MessageDigest

fun String.computedMD5Hash(): String {
    val digested = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return digested.toHexString()
}

fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }

fun Context.getStringByReference(textReference: TextReference): String {
    return when(textReference) {
        is TextReference.Resource -> getString(textReference.stringId, *textReference.formatArgs.toTypedArray())
        is TextReference.PluralResource -> resources.getQuantityString(textReference.id, textReference.count, *textReference.formatArgs.toTypedArray())
        is TextReference.Str -> textReference.value
    }
}

fun TextView.setTextByReference(textReference: TextReference?) {
    text = if (textReference == null) null else context.getStringByReference(textReference)
}

fun View.setViewVisibility(visibility: ViewVisibility) {
    this.visibility = visibility.value
}