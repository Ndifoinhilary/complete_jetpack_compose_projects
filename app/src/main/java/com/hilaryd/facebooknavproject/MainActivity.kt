package com.hilaryd.facebooknavproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.catalin.fblikeapp.data.Shortcut
import com.catalin.fblikeapp.data.getRandomItems
import com.hilaryd.facebooknavproject.destinations.Destinations
import com.hilaryd.facebooknavproject.screens.FbBottomNav
import com.hilaryd.facebooknavproject.screens.HomeScreen
import com.hilaryd.facebooknavproject.screens.ItemDetailsScreen
import com.hilaryd.facebooknavproject.screens.NavigationDrawer
import com.hilaryd.facebooknavproject.screens.NotificationScreen
import com.hilaryd.facebooknavproject.ui.theme.FaceBookNavProjectTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaceBookNavProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    FbScaffold(navController = navController)
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FbScaffold(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val ctx = LocalContext.current
    val listOfItems by remember {
        mutableStateOf(getRandomItems(10))
    }
    val shortCuts by remember {
        mutableStateOf(Shortcut.getShortcuts())
    }
    val onDrawerIconClick: () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }
    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { FbBottomNav(navController = navController, onDrawerIconClick) },
        drawerContent = { NavigationDrawer(navController = navController) }

    ) { padding ->
        val modifier = Modifier
            .padding(bottom = padding.calculateBottomPadding(), top = padding.calculateTopPadding())
            .background(Color(0xffcccccc))
        NavHost(navController = navController, startDestination = Destinations.Home.route) {
            composable(Destinations.Home.route) {
                HomeScreen(
                    navController = navController,
                    modifier = modifier
                )
            }
            composable(Destinations.Notification.route) {
                NotificationScreen(listOfItems, shortCuts)
            }
            composable(
                Destinations.Detail.route,
                deepLinks = listOf(navDeepLink {
                    uriPattern = "https://www.facebooknavproject/{itemId}"
                })
            ) {
                val itemId = it.arguments?.getString("itemId")
                if (itemId == null) {
                    Toast.makeText(ctx, "Oops an error", Toast.LENGTH_LONG).show()
                } else {
                    ItemDetailsScreen(itemId.toInt(), modifier)
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FaceBookNavProjectTheme {

    }
}