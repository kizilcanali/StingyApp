package com.alikizilcan.stingyapp.infra.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController


class NavigationObserver{

    fun observeNavigation(
        navigation: Navigation,
        navController: NavController,
        lifecycleOwner: LifecycleOwner
    ){
        navigation.navigateTo.observe(lifecycleOwner) { directions ->
            directions?.let {
                navController.navigate(it)
                navigation.onNavigationComplete()
            }
        }
    }

}