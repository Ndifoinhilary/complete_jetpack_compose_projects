package com.hilaryd.facebooknavproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.catalin.fblikeapp.data.Dessert
import com.catalin.fblikeapp.data.Fruit
import com.catalin.fblikeapp.data.ListItem
import com.catalin.fblikeapp.data.Person
import com.catalin.fblikeapp.data.Shortcut
import com.hilaryd.facebooknavproject.R

@Composable
fun NotificationScreen(randomItem: List<ListItem>, shortcut: List<Shortcut>) {
    Column(modifier = Modifier.background(Color(0xffeeeeee))) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .clip(
                        CircleShape
                    )
            )
            Text(text = "Hilary", modifier = Modifier.padding(8.dp))
        }
        Text(text = "Your shortcuts", modifier = Modifier.padding(8.dp))

        LazyRow {
            items(randomItem) {
                var shape: Shape = RoundedCornerShape(8.dp)
                var restId: Int = 0
                var title: String? = null
                when (it::class) {
                    Dessert::class -> {
                        restId = (it as Dessert).resId
                        title = it.title

                    }

                    Fruit::class -> {
                        restId = (it as Fruit).id
                        title = it.name
                    }

                    Person::class -> {
                        shape = CircleShape
                        restId = (it as Person).id
                        title = it.name
                    }
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = restId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape),
                        contentScale = ContentScale.Crop
                    )
                    title?.let { Text(text = it, fontSize = 12.sp) }
                }


            }
        }
        Text(text = "All shortcuts", modifier = Modifier.padding(8.dp))

        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
            items(shortcut){

            }
        }
    }
}