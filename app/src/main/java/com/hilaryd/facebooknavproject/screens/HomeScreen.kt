package com.hilaryd.facebooknavproject.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.catalin.fblikeapp.data.Dessert
import com.catalin.fblikeapp.data.Fruit
import com.hilaryd.facebooknavproject.destinations.Destinations

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier) {
    val deserts by remember {
        mutableStateOf(Dessert.getAllDesserts())
    }

    val fruits by remember {
        mutableStateOf(Fruit.getAllFruits())
    }

    val pageSize = 5
    var currentPage = 0

    LazyColumn(modifier) {
        val desertSize = deserts.size
        while (desertSize > currentPage * pageSize) {
            val from = currentPage * pageSize
            var to = (currentPage + 1) * pageSize
            if (desertSize < to)
                to = desertSize

            var nextDesert = deserts.subList(from, to)

            items(nextDesert) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp)
                    .background(Color.White)
                    .clickable {
                        navController.navigate(Destinations.Detail.createRoute(it.id))
                    }
                ) {
                    Text(
                        text = it.title,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = it.desc,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                    Image(
                        painter = painterResource(id = it.resId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            val fruitsFrom = currentPage * pageSize
            var fruitsTo = (currentPage + 1) * pageSize

            if (fruits.size < fruitsTo)
                fruitsTo = fruits.size

            var nextFruits = fruits.subList(fruitsFrom, fruitsTo)

            item {
                LazyRow(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .height(250.dp)
                ) {
                    items(nextFruits) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(150.dp)
                                .padding(4.dp)
                                .clip(
                                    RoundedCornerShape(4.dp)
                                )
                                .clickable {
                                    navController.navigate(Destinations.Detail.createRoute(it.id))
                                }
                        ) {
                            Image(
                                painter = painterResource(id = it.resId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillHeight
                            )
                            Text(
                                text = it.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xaaffffff))
                                    .padding(4.dp)
                            )
                        }
                    }


                }
            }

            currentPage += 1


        }
    }
}