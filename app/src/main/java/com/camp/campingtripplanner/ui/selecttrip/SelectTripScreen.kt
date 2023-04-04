package com.camp.campingtripplanner.ui.selecttrip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.camp.campingtripplanner.MainScreen
import com.camp.campingtripplanner.NavScreen
import com.camp.campingtripplanner.ui.createtrip.CreateTripScreen

@Composable
fun SelectTripScreen(onTripSelected: (Int) -> Unit, viewModel: SelectTripViewModel) {
    val argumentNumber = 4
//    val trips = viewModel.campingTrips
//    val selectTripViewModel: SelectTripViewModel = viewModel()

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Select Trip Screen")
            Text(text = "Trips: ${viewModel.campingTrips}")
            Button(onClick = { onTripSelected(argumentNumber) }) {
                Text(text = "Tap To Select Default Trip")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val navController = rememberNavController()
//    SelectTripScreen {
//        navController.navigate(NavScreen.TripDetailScreen.route + "/${it}")
//    }
//}
