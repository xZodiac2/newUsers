package com.ilya.usersupgrade

import android.view.View

fun View.show() {
    if (this.visibility == View.VISIBLE) return

    this.visibility = View.VISIBLE
}

fun View.hide() {
    if (this.visibility == View.GONE) return

    this.visibility = View.GONE
}