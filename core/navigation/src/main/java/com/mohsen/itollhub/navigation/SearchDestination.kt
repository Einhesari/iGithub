package com.mohsen.itollhub.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohsen.itollhub.search.SearchRoute

object SearchDestination : Destination {
    override val route: String = "search_destination"

}

fun NavGraphBuilder.searchDestination(navigateTo: (Int) -> Unit) {
    composable(route = SearchDestination.route) {
        SearchRoute(onItemClicked = navigateTo)
    }
}