package com.example.myapplicationn.screens.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationn.helpers.extractNumericPart
import com.example.myapplicationn.navigation.Screen
import com.example.myapplicationn.screens.page.components.GoldenRetrieverImage
import com.example.myapplicationn.screens.page.components.StatsRow
import com.example.myapplicationn.viewModel.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePage(
    viewModel: SearchViewModel,
    onNavigate: (id: Int) -> Unit,
    postListNavigate: (id: String) -> Unit
) {
    val state by viewModel.stateFlow.collectAsState()

    val dynamicPostId = remember { mutableStateOf(-1) }

    // Function to update the post ID when the button is clicked
    val updatePostId: () -> Unit = {
        val postId = extractNumericPart(state.searchText)
        if (postId != null && postId in 0..22) {
            dynamicPostId.value = postId
            viewModel.updateSinglePostId(postId)
        }
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

        GoldenRetrieverImage()

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.searchText,
            onValueChange = { newText ->
                viewModel.updateSearchText(newText)
                updatePostId() // Call updatePostId whenever search text changes
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Dismiss Snackbar before performing the search
                    viewModel.dismissSnackbar()
                    // Call performSearch with the query and viewModel to update search results
                    viewModel.performSearch(state.searchText)
                }
            ),
            placeholder = {
                Text(text = "Search")
            },
            trailingIcon = {
                // Add the search icon button
                IconButton(
                    onClick = {
                        if (dynamicPostId.value in 0..22) {
                            onNavigate(dynamicPostId.value)
                        } else {
                            // Show an error Snackbar only if the ID is invalid
                            val postId = state.searchText.toIntOrNull()
                            if (postId == null || postId !in 0 until 22) {
                                viewModel.showSnackbar("Invalid post ID entered.")
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black
                    )
                }
            }
        )

        // DisposableEffect to clear search text when navigating to PostDetail
        DisposableEffect(Unit) {
            onDispose {
                if (dynamicPostId.value >= 0) {
                    viewModel.updateSearchText("")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            onClick = {
                // Clear search text and navigate to PostList
                viewModel.updateSearchText("")
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

        // Show the error Snackbar if showErrorSnackbar is true and the search is invalid
        if (state.showErrorSnackbar && !state.isSearching) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    Button(onClick = { viewModel.dismissSnackbar() }) {
                        Text("Dismiss")
                    }
                }
            ) {
                Text(state.snackbarMessage)
            }
        }
    }
}