package com.droidbaza.navigationboostersample.navigation

import androidx.navigation.NavHostController
import com.droidbaza.navigationbooster.NavigationUtils.isDestinationExist
import com.droidbaza.navigationbooster.NavigationUtils.navigate
import com.droidbaza.navigationbooster.NavigationUtils.popBackStack


class NavigationController(val hostController: NavHostController) {

    fun navigate(
        destination: Destination,
        vararg args: Pair<String, Any> = emptyArray(),
        launchSingleTop: Boolean = true,
        restoreState: Boolean = true,
        popupDestination: Destination? = null,
    ) {
        hostController.navigate(
            destination.route,
            args,
            launchSingleTop = launchSingleTop,
            restoreState = restoreState,
            popUpTo = popupDestination?.route
        )
    }

    fun navigate(
        route: String,
        vararg args: Pair<String, Any> = emptyArray(),
        launchSingleTop: Boolean = true,
        restoreState: Boolean = true,
        popupDestination: Destination? = null,
    ) {
        hostController.apply {
            if (isDestinationExist(route)) {
                popBackStack(route, false)
            } else {
                navigate(
                    route,
                    args,
                    launchSingleTop = launchSingleTop,
                    restoreState = restoreState,
                    popUpTo = popupDestination?.route
                )
            }
        }
    }

    fun popBackStack(
        args: Pair<String, Any>? = null,
        destination: Destination? = null,
        inclusive: Boolean = false,
        saveState: Boolean = false
    ) {
        hostController.popBackStack(args, destination?.route, inclusive, saveState)
    }
}

