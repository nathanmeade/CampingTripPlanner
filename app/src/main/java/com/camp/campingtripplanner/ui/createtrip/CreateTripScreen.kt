package com.camp.campingtripplanner.ui.createtrip

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
import com.camp.campingtripplanner.NavScreen
import com.camp.campingtripplanner.ui.home.HomeScreen

@Composable
fun CreateTripScreen(onTripCreated: (Int) -> Unit, viewModel: CreateTripViewModel) {
    val argumentNumber = 6
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
                onClick = { viewModel.createTrip(onTripCreated) }
            ) {
                Text(
                    text = "CREAasdfTE TRIP",
                    fontSize = 40.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onTripCreated(argumentNumber) }
            ) {
                Text(
                    text = "Tap For TripDetail",
                    fontSize = 40.sp
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val navController = rememberNavController()
//    CreateTripScreen {
//        navController.navigate(NavScreen.TripDetailScreen.route + "/${it}")
//    }
//}
