package com.camp.campingtripplanner.ui.tripdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.camp.campingtripplanner.NavScreen
import com.camp.campingtripplanner.ui.createtrip.CreateTripScreen

@Composable
fun TripDetailScreen(tripId: Int?) {
//    val number = 6
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TripDetailScreen(5)
}
