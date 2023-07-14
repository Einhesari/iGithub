package com.mohsen.itollhub.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mohsen.itollhub.search.SearchScreen

object SearchDestination : Destination {
    override val route: String = "search_destination"

}

fun NavGraphBuilder.searchDestination(navigateTo: (String) -> Unit) {
    composable(route = SearchDestination.route) {
        SearchScreen(onItemClicked = navigateTo)
    }
}