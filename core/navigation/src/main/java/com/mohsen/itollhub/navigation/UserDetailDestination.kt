package com.mohsen.itollhub.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mohsen.itollhub.detail.UserDetailRoute

object UserDetailDestination : Destination {
    override val route: String = "userDetail"
    const val argKey = "userName"
    val routeWithArgs = "$route/{$argKey}"
    val arguments = listOf(navArgument(argKey) {
        type = NavType.StringType
    })
}

fun NavGraphBuilder.userDetailDestination() {
    composable(
        route = UserDetailDestination.routeWithArgs,
        arguments = UserDetailDestination.arguments
    ) {
        val userName = it.arguments?.getString(UserDetailDestination.argKey) ?: ""
        UserDetailRoute(userName = userName)
    }
}