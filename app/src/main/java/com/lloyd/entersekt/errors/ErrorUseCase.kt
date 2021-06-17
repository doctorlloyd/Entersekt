package com.lloyd.entersekt.errors

import com.lloyd.entersekt.data.error.Error
interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
