package com.camp.campingtripplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.camp.campingtripplanner.ui.createtrip.CreateTripScreen
import com.camp.campingtripplanner.ui.home.HomeScreen
import com.camp.campingtripplanner.ui.selecttrip.SelectTripScreen
import com.camp.campingtripplanner.ui.tripdetail.TripDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
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
                },
                onSelectTripClicked = {
                    navController.navigate(NavScreen.SelectTripScreen.route)
                }
            )
        }
        composable(route = NavScreen.CreateTripScreen.route) {
            CreateTripScreen {
                navController.navigate(NavScreen.TripDetailScreen.route + "/${it}") {
//
                }
            }
        }
        composable(route = NavScreen.SelectTripScreen.route) {
            SelectTripScreen {
                navController.navigate(NavScreen.TripDetailScreen.route + "/${it}") {
//                    it.arguments()
                }
            }
        }
        composable(
            route = NavScreen.TripDetailScreen.route + "/{tripId}",
            arguments = listOf(
                navArgument("tripId") {
                    type = NavType.IntType
                }
            )
        ) {
            TripDetailScreen(
                it.arguments?.getInt("tripId")
            )
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
    object SelectTripScreen : NavScreen("SelectTripScreen")
    object TripDetailScreen : NavScreen("TripDetailScreen")
}
