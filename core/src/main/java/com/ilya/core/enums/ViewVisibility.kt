package com.ilya.core.enums

import android.view.View

enum class ViewVisibility(val value: Int) {
    VISIBLE(View.VISIBLE),
    INVISIBLE(View.INVISIBLE),
    GONE(View.GONE);
}