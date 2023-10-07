package com.example.myapplicationn

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilePage() {
    val customElevation = CardDefaults.cardElevation(6.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(2.dp, Color.Black, shape = RoundedCornerShape(30.dp))
    ) {
        Card(
            elevation = customElevation,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.golden_retriever),
                    contentDescription = "golden retriever",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(width = 4.dp, color = Color.Red),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Retriever",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "England",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Stats(count = "22", title = "Posts")
                    Stats(count = "150", title = "Followers")
                    Stats(count = "100", title = "Following")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { /* Follow action */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Follow User")
                    }

                    Button(
                        onClick = { /* Message action */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Direct Message")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Add a header for the posts section
        Text(
            text = "Posts",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Group the items in triples and display them in rows
        val chunkedItems = (1..22).chunked(3)
        chunkedItems.forEachIndexed { index, chunk ->
            if (index == chunkedItems.indexOfFirst { it.contains(19) }) {
                // Place "items22" below "item19"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    chunk.forEach { postNumber ->
                        Card(
                            elevation = customElevation,
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = "Item $postNumber")
                            }
                        }
                    }
                }
            } else {
                // Display regular chunks
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    chunk.forEach { postNumber ->
                        Card(
                            elevation = customElevation,
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = "Item $postNumber")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Stats(count: String, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}
