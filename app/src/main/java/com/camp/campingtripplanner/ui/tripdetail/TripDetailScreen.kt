package com.camp.campingtripplanner.ui.tripdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.camp.campingtripplanner.NavScreen
import com.camp.campingtripplanner.OpenWeatherResponse
import com.camp.campingtripplanner.ui.createtrip.CreateTripScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import retrofit2.Call

@Composable
fun TripDetailScreen(tripId: Int?, viewModel: TripDetailViewModel) {
    val weather by viewModel.weather.collectAsState()
    viewModel.getCurrentWeather()
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
            Text(text = "Trip Detail Screen: $tripId")
            Text(text = "Trip Detail Screen: ${weather.temp}")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    TripDetailScreen(5)
//}
