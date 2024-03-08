package com.example.myapplicationn.screens.postlist


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationn.data.Post
import com.example.myapplicationn.viewModel.SearchViewModel

@Composable
fun PostList(
    viewModel: SearchViewModel,
    onNavigateToProfile: () -> Unit,
    onNavigateToPostDetail: (Int) -> Unit // Function to navigate to PostDetail
) {
    // Create a list of posts
    val posts = List(22) { Post(id = it, content = "This is post $it content.") }

    // Call generateInitialPosts with the list of posts
    LaunchedEffect(Unit) {
        viewModel.generateInitialPosts(posts)
    }


    val h4Style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.15.sp
    )

    // Wrap the content in a Box
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Use BoxScope to position your content
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Posts",
                    style = h4Style
                )

                Spacer(modifier = Modifier.weight(1f)) // Pushes the button to the right

                // Add the button to navigate to the Profile page
                // button to navigate to the Profile page
                Button(
                    onClick = onNavigateToProfile
                ) {
                    Text("Go to Profile")
                }
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
                                onNavigateToPostDetail(post.id) // Navigate to PostDetail with the post ID
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
        }
    }
}

