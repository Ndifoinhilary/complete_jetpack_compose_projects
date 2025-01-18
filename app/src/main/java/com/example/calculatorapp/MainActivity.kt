package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorAppTheme {
                MyApp {
                    Text(text = "Hello and how are you doing")
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(text = "Hi and how are you doing", modifier = Modifier.padding(innerPadding))
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorAppTheme {
        MyApp {
            Text(text = "Hello world and how are you doing today")
        }
    }
}