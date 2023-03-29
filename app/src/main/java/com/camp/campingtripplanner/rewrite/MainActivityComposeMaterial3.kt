package com.camp.campingtripplanner.rewrite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.camp.campingtripplanner.rewrite.ui.CreateTripScreen
import com.camp.campingtripplanner.rewrite.ui.HomeScreen

//@AndroidEntryPoint
class MainActivityComposeMaterial3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.HomeScreen.route) {
        composable(route = NavScreen.HomeScreen.route) {
            HomeScreen(
                onCreateTripClicked = {
                    navController.navigate(NavScreen.CreateTripScreen.route)
                }
            )
        }
        composable(route = NavScreen.CreateTripScreen.route) {
            CreateTripScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen("HomeScreen")
    object CreateTripScreen : NavScreen("CreateTripScreen")
}
