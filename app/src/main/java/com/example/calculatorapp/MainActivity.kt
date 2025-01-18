package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun TopHeader(modifier: Modifier = Modifier, dataPerson: Double = 0.0) {
    Surface(
        modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(corner = CornerSize(4.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val total = "%.2f".format(dataPerson)
            Text(text = "Total per person", style = MaterialTheme.typography.titleSmall)
            Text(text = "$$total", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold)
        }
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