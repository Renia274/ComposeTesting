package com.example.myapplicationn


import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object Profile : Screen("profile")
    object PostList : Screen("post_list")
    object PostDetail : Screen("post_detail/{postId}", listOf(navArgument("postId") { type = NavType.IntType }))
}


@Composable
fun SetupNavigation(navController: NavHostController, viewModel: SearchViewModel) {


    NavHost(navController = navController, startDestination = Screen.Profile.route) {
        composable(route = Screen.Profile.route) {
            ProfilePage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.PostList.route) {
            PostList(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.PostDetail.route,
            arguments = Screen.PostDetail.arguments
        ) { navBackStackEntry ->
            val postId = navBackStackEntry.arguments?.getInt("postId")
            if (postId != null) {
                PostDetail(postId = postId)
            }
        }
    }
}