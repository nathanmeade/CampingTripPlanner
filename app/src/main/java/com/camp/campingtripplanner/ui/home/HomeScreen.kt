package com.camp.campingtripplanner.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.camp.campingtripplanner.MainScreen
import com.camp.campingtripplanner.NavScreen

@Composable
fun HomeScreen(
    onCreateTripClicked: () -> Unit,
    onSelectTripClicked: () -> Unit
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    onCreateTripClicked()
                }
            ) {
                Text(
                    text = "CREATE TRIP",
                    fontSize = 40.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    onSelectTripClicked()
                }
            ) {
                Text(
                    text = "SELECT TRIP",
                    fontSize = 40.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    HomeScreen(
        onCreateTripClicked = {
            navController.navigate(NavScreen.CreateTripScreen.route)
        },
        onSelectTripClicked = {
            navController.navigate(NavScreen.SelectTripScreen.route)
        }
    )
}
