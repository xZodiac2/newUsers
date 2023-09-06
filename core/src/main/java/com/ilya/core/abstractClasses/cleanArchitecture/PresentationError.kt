package com.ilya.core.abstractClasses.cleanArchitecture

import com.ilya.core.TextReference
import com.ilya.core.abstractClasses.UsersApplicationError

abstract class PresentationError(open val textReference: TextReference) : UsersApplicationError()