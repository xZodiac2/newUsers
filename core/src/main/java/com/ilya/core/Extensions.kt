package com.ilya.core

import android.content.Context
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import java.security.MessageDigest

fun String.computedMD5Hash(): String {
    val digested = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    val stringBuilder = StringBuilder()
    
    digested.forEach { byte -> stringBuilder.append(String.format("%02x", byte)) }
    
    return stringBuilder.toString()
}

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