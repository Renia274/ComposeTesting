package com.example.myapplicationn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoldenRetrieverView() {
    // Create any necessary state variables or data for this view
    var someState by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Golden Retriever in England",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.15.sp
            ),
            modifier = Modifier.padding(16.dp)
        )

        // Display the Golden Retriever image
        GoldenRetrieverImage()

        // Add any additional content or controls here
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun GoldenRetrieverImage() {
    // Adjust the padding and background color as needed
    val padding = 8.dp // Adjust the padding value as needed

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .clip(CircleShape) // Clip the entire Card to a circular shape
    ) {
        Image(
            painter = painterResource(id = R.drawable.golden_retriever),
            contentDescription = "Jerry the Golden Retriever",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f) // Adjust the aspect ratio as needed
        )
    }
}


