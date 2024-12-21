package xyz.androidrey.weatherapp.home.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import live.studyquran.android.common.ui.screen.HomeScreen
import xyz.androidrey.weatherapp.home.ui.home.HomeScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeScreen.Main) {
        composable<HomeScreen.Main> {
            HomeScreen(hiltViewModel(), navController)
        }
    }
}