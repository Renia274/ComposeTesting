package com.example.myapplicationn.screens.page

import android.widget.Toast
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationn.helpers.extractNumericPart
import com.example.myapplicationn.screens.page.components.GoldenRetrieverImage
import com.example.myapplicationn.screens.page.components.StatsRow
import com.example.myapplicationn.ui.theme.MyApplicationnTheme
import com.example.myapplicationn.viewModel.SearchViewModel




@Composable
fun ProfilePage(
    viewModel: SearchViewModel,
    onNavigate: (Int) -> Unit,
    postListNavigate: (String) -> Unit
) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }

    val state by viewModel.stateFlow.collectAsState()

    // Extract necessary state information
    val isSearching = state.isSearching
    val posts = state.posts

    Column {
        ProfileContent(
            searchText = searchText,
            onSearchTextChange = { newText -> searchText = newText },
            onSearch = {
                val postId = extractNumericPart(searchText)
                if (!isSearching && posts.any { it.id == postId }) {
                    // Navigate to the detail screen of the specified post
                    onNavigate(postId)
                } else {
                    // Handle the case when the specified post ID does not exist
                    Toast.makeText(context, "This post is not inside the list", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}


@Composable
fun ProfileContent(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearch: () -> Unit
) {

    val h4Text = TextStyle(
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
            text = "Jerry Parker",
            style = h4Text,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        GoldenRetrieverImage()

        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            textStyle = TextStyle(color = Color.Black),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            ),
            placeholder = {
                Text(text = "Search")
            },
            trailingIcon = {
                IconButton(onClick = onSearch) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display user stats
        StatsRow()

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {

    MyApplicationnTheme {
        ProfileContent(
            searchText = "post0",
            onSearchTextChange = {},
            onSearch = {}
        )
    }
}
