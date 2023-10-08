package com.example.myapplicationn


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationn.ui.theme.MyApplicationnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Initialize the SearchViewModel using the viewModel() function
            val viewModel: SearchViewModel = viewModel()

            MyApplicationnTheme {
                SetupNavigation(navController = navController, viewModel = viewModel)
            }
        }
    }
}




/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    var nameState by remember { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf(name) } // Initialize 'name' with the provided 'name' parameter

    // Arrangement in the center horizontally and vertically
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {

        // Text to display the greeting message
        Text(text = "Hello $name")

        // TextField for user input
        TextField(
            value = nameState,
            onValueChange = {
                nameState = it
            },
            label = { Text(text = "Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Add a vertical spacer

        // Button to display the greeting only if the input is valid
        Button(
            onClick = {
                if (isValidText(nameState)) {
                    name = nameState
                } else {
                    // Display a validation message if the input is not valid
                    // You can customize this message as needed
                    name = "Invalid Name"
                }
            }
        ) {
            Text(text = "Display")
        }

        // Validation message for custom validation rules
        if (!isValidText(nameState)) {
            Text(text = "Invalid input", color = Color.Red)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Validation() {
    var textInput by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    TextField(
        value = textInput,
        onValueChange = { input ->
            textInput = input
            isError = !isValidText(input)
        },
        label = { Text("Enter Text") },
        isError = isError
    )

    if (isError) {
        Text(text = "Invalid input", color = Color.Red)
    }
}


fun isValidText(text: String): Boolean {
    // Add your custom validation rules here
    return text.matches(Regex("[a-zA-Z]+"))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting("Preview Name") // Pass a name to the preview
}*/