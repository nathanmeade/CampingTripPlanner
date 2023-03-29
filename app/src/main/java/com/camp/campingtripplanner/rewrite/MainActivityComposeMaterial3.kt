package com.camp.campingtripplanner.rewrite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.camp.campingtripplanner.ui.theme.CampingTripPlannerTheme

//@AndroidEntryPoint
class MainActivityComposeMaterial3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            content()
        }
    }
}

@Composable
fun content() {
    MaterialTheme {
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
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "CREATE TRIP",
                        fontSize = 40.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "SELECT TRIP",
                        fontSize = 40.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    content()
}
