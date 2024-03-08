package com.example.myapplicationn.screens.page.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplicationn.R


@Composable
fun GoldenRetrieverImage() {

    val padding = 8.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.golden_retriever),
            contentDescription = "Jerry the Golden Retriever",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )
    }
}