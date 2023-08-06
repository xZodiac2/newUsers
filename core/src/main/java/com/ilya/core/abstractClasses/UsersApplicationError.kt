package com.ilya.core.abstractClasses

import androidx.annotation.StringRes

abstract class UsersApplicationError(@StringRes open val stringId: Int) : Throwable()