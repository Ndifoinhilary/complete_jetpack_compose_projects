package com.hilaryd.facebooknavproject.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hilaryd.facebooknavproject.destinations.Destinations

@Composable
fun FbBottomNav(navController: NavController, onDrawerIconClick: () -> Unit) {
    BottomNavigation(modifier = Modifier.padding(bottom = 4.dp)) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination
        BottomNavigationItem(
            selected = currentDestination?.route == Destinations.Home.route,
            onClick = { navController.navigate(Destinations.Home.route) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = Destinations.Home.route)},
            modifier = Modifier.padding(bottom = 4.dp)
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Destinations.Notification.route,
            onClick = { navController.navigate(Destinations.Notification.route) },
            icon = { Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notification") },
            label = { Text(text = Destinations.Notification.route)},
            modifier = Modifier.padding(bottom = 4.dp)

        )

        BottomNavigationItem(
            selected = false,
            onClick = onDrawerIconClick,
            icon = { Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu") },
            label = { Text(text = "Menu")},
            modifier = Modifier.padding(bottom = 4.dp)

        )
    }
}