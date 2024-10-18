package com.hilaryd.facebooknavproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hilaryd.facebooknavproject.destinations.Destinations
import com.hilaryd.facebooknavproject.screens.FbBottomNav
import com.hilaryd.facebooknavproject.screens.HomeScreen
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
fun  FbScaffold(navController: NavHostController){
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val onDrawerIconClick: () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }
    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { FbBottomNav(navController = navController, onDrawerIconClick) },
        drawerContent = { NavigationDrawer(navController = navController)}

    ) {
       NavHost(navController = navController, startDestination = Destinations.Home.route) {
           composable(Destinations.Home.route) { HomeScreen(navController = navController) }
           composable(Destinations.Notification.route) { NotificationScreen(navController = navController) }
       }
    }
}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FaceBookNavProjectTheme {

    }
}