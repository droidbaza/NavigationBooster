package com.droidbaza.navigationbooster

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import com.droidbaza.navigationbooster.navigation.Destination
import com.droidbaza.navigationbooster.navigation.NavigationController
import com.droidbaza.navigationbooster.navigation.NavigationHost

@Composable
fun MainContent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MainScreen()
    }
}

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    startDestination: Destination = Destination.First,
) {
    val controller = remember(navController) {
        NavigationController(navController)
    }
    NavigationHost(controller, startDestination.route)
}

