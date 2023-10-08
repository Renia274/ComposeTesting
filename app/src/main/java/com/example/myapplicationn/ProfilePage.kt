package com.example.myapplicationn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(navController: NavController, viewModel: SearchViewModel) {
    var searchText by remember { mutableStateOf("") }

    // Create a remember for the searchResults list
    val searchResultsList = remember { mutableListOf<String>() }

    // Create a mutableStateOf for searchResults to trigger recomposition
    val searchResults by remember { mutableStateOf<List<String>>(searchResultsList) }

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
            text = "Profile Page",
            style = h4Text,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // You can customize the background color
                .padding(8.dp),
            textStyle = TextStyle(color = Color.Black), // You can customize text appearance
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search // Customize keyboard actions
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    performSearch(searchText, searchResultsList)
                }
            ),
            placeholder = {
                Text(text = "Search")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.PostList.route) },
            elevation = CardDefaults.cardElevation(4.dp)
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

        // Display search results
        SearchResults(searchResults)
    }
}

@Composable
fun SearchResults(results: List<String>) {
    Column {
        Text("Search Results:")
        results.forEach { result ->
            Text(result)
        }
    }
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

fun performSearch(query: String, searchResults: MutableList<String>) {

    val results = listOf("Result 1 for '$query'", "Result 2 for '$query'", "Result 3 for '$query'")

    // Update the search results
    searchResults.clear()
    searchResults.addAll(results)
}
