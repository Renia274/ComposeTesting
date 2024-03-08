package com.example.myapplicationn


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationn.navigation.Screen
import com.example.myapplicationn.ui.theme.MyApplicationnTheme




@Composable
fun PostDetail(
    postId: Int,
    onNavigate: (String) -> Unit
) {
    val h4TextStyle = TextStyle(
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
            text = "Post Detail",
            style = h4TextStyle,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PostDetailContent(postId = postId)

        Spacer(modifier = Modifier.height(16.dp))

        // button to navigate to post list
        Button(
            onClick = {
                onNavigate(Screen.PostList.route)
            }
        ) {
            Text("Go Back to Post List")
        }
    }
}

@Composable
fun PostDetailContent(postId: Int) {
    val postContent = "This is post $postId content."
    val postDetailElevation = CardDefaults.cardElevation(6.dp)
    val h6TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        elevation = postDetailElevation
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Post $postId",
                style = h6TextStyle,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = postContent)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailPreview() {

    MyApplicationnTheme {
        PostDetail(postId = 1, onNavigate = {})
    }
}
