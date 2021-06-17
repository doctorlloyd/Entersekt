package com.lloyd.entersekt.ui.base

import androidx.lifecycle.ViewModel
import com.lloyd.entersekt.errors.ErrorManager
import javax.inject.Inject


abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var errorManager: ErrorManager
}
