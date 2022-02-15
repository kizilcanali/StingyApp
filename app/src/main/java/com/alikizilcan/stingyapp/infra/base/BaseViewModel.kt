package com.alikizilcan.stingyapp.infra.base

import androidx.lifecycle.ViewModel
import com.alikizilcan.stingyapp.infra.navigation.Navigation

abstract class BaseViewModel : ViewModel(){
    val baseNavigation = Navigation()
}