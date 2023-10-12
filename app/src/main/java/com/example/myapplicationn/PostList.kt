package com.example.myapplicationn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostList(
    viewModel: SearchViewModel,
    onNavigateToProfile: () -> Unit // Function to navigate to Profile
) {
    // Create a list of posts (you can replace this with your actual data)
    val posts = List(22) { Post(id = it, content = "This is post $it content.") }

    val h4Style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.15.sp
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Posts",
            style = h4Style,
            modifier = Modifier.padding(16.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
    ) {
        items(posts) { post ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Handle click on a post
                        // You can perform some action here if needed
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = post.content)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    // Add a button to navigate to the Profile page
    Button(
        onClick = onNavigateToProfile
    ) {
        Text("Go to Profile")
    }
}

