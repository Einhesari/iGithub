package com.mohsen.itollhub

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mohsen.itollhub.navigation.DummyDestination

@Composable
fun ItollHubNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = DummyDestination.route,
    ) {
    }
}