package com.example.myapplicationn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProfilePage(viewModel: SearchViewModel,onNavigate:(id:Int)->Unit,postListNavigate:(id:String)->Unit) {
    var searchText by remember { mutableStateOf("") }

    // Observe the search results and posts using collectAsState
    val posts by viewModel.posts.collectAsState()

    // Property to track whether a search is in progress
    val isSearching: Boolean by viewModel.isSearching.collectAsState()

    val dynamicPostId: MutableState<Int> = remember { mutableIntStateOf(1) }

    // Function to update the post ID when the button is clicked
    val updatePostId: () -> Unit = {
        val newId = extractNumericPart(searchText)
        dynamicPostId.value = newId
        viewModel.updateSinglePostId(newId)
    }



    val h4Text = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.15.sp
    )

    val h6Text = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Jerry Parker",
            style = h4Text,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display the Golden Retriever image
        GoldenRetrieverImage()

        Spacer(modifier = Modifier.height(16.dp))

        // In your ProfilePage composable
        TextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            textStyle = TextStyle(color = Color.Black), // customize text appearance
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search // Customize keyboard actions
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Call performSearch with the query and viewModel to update search results
                    viewModel.performSearch(searchText)
                }
            ),
            placeholder = {
                Text(text = "Search")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            onClick = {
                // Use onNavigate to navigate to the PostList screen
                postListNavigate(Screen.PostList.route)
            }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "View Posts",
                    style = h6Text
                )
            }
        }




        Spacer(modifier = Modifier.height(16.dp))

        // Display user stats
        StatsRow()

        Spacer(modifier = Modifier.height(16.dp))

        // Button to change the dynamically changing post ID and navigate to the detail screen
        Button(
            onClick = {
                updatePostId()
                // Navigate to the PostDetail route with the selected post ID
                //navController.navigate(Screen.PostDetail.route.replace("{postId}", dynamicPostId.value.toString()))
                onNavigate(dynamicPostId.value)
            }
        ) {
            Text("Go to Post")
        }

    }
}


fun extractNumericPart(input: String): Int {
    val regex = Regex("\\d+")
    val matchResult = regex.find(input)
    return matchResult?.value?.toIntOrNull() ?: 0
}




@Composable
fun StatsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatsItem(count = "22", title = "Posts")
        StatsItem(count = "150", title = "Followers")
        StatsItem(count = "100", title = "Following")
    }
}

@Composable
fun StatsItem(count: String, title: String) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title, fontWeight = FontWeight.Bold)
    }
}


