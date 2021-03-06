package com.comye1.capstone.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(toNext: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(500L)
        toNext()
    }
    Log.d("capstone", "splash")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Capstone", style = MaterialTheme.typography.h3, fontWeight = FontWeight.Bold)
        Text(text = "Design", style = MaterialTheme.typography.h3, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(50.dp))
//        Image(
//            painter = painterResource(id = R.drawable.driving_img),
//            contentDescription = "driving image",
//            modifier = Modifier.size(200.dp)
//        )
    }
}