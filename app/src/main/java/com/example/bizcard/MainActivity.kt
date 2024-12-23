package com.example.bizcard

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreateBizCard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(modifier: Modifier = Modifier) {
    var expended by remember {
        mutableStateOf(false)
    }
    Surface(modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .size(height = 200.dp, width = 390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(8.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CreateProfileImage(modifier)
                HorizontalDivider(modifier.padding(5.dp), thickness = 4.dp, color = Color.LightGray)
                CreateInfo(modifier)
                Button(onClick = { expended = !expended }) {
                    Text(text = "More about me")
                }
                if (expended) {
                    Content()
                } else {
                    Box {

                    }
                }
            }

        }
    }
}

@Composable
private fun CreateInfo(modifier: Modifier) {
    Column(
        modifier.padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mr Smith", fontSize = 24.sp, color = Color.Blue)

        Text(
            text = "Software Engineer",
            modifier.padding(3.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "@Bydefault",
            modifier.padding(3.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier) {
    Surface(
        modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        tonalElevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop,
        )
    }
}
data class ProfileData(val image: Painter, val title: String, val content: String)

@Composable
fun Content(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Surface(
            modifier
                .padding(3.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(5.dp, color = Color.LightGray)
        ) {
            val data = listOf(
                ProfileData(
                    image = painterResource(id = R.drawable.profile),
                    title = "Jane Doe",
                    content = "Senior Software Engineer at TechCorp"
                ),
                ProfileData(
                    image = painterResource(id = R.drawable.profile),
                    title = "John Smith",
                    content = "Product Manager at InnovateX"
                ),
                ProfileData(
                    image = painterResource(id = R.drawable.profile),
                    title = "Alice Johnson",
                    content = "UI/UX Designer with a passion for creativity"
                ),
                ProfileData(
                    image = painterResource(id = R.drawable.profile),
                    title = "Bob Brown",
                    content = "Android Developer specializing in Kotlin and Jetpack Compose"
                )
            )

            Portfolio(data)
        }
    }
}

@Composable
fun Portfolio(data: List<ProfileData>) {

    LazyColumn {
        items(data) { item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(), shape = RectangleShape) {
                Profile(image = item.image, title = item.title, content = item.content)
            }
        }
    }
}


@Composable
fun Profile(image: Painter, title: String, content: String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = image, contentDescription = title, modifier = Modifier
                .size(100.dp)
                .clip(CircleShape))

            Column(modifier = Modifier
                .padding(4.dp)
                .weight(1f),

            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(text = content, style = MaterialTheme.typography.bodyMedium)
            }

        }
        HorizontalDivider()
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizCardTheme {
        CreateBizCard()
    }
}