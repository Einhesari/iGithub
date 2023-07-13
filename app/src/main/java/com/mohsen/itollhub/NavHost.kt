package com.mohsen.itollhub

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mohsen.itollhub.navigation.SearchDestination
import com.mohsen.itollhub.navigation.UserDetailDestination
import com.mohsen.itollhub.navigation.searchDestination
import com.mohsen.itollhub.navigation.userDetailDestination

@Composable
fun ItollHubNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SearchDestination.route,
    ) {
        searchDestination {
            navController.navigate("${UserDetailDestination.route}/$it")
        }
        userDetailDestination()
    }
}