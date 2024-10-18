package com.hilaryd.facebooknavproject.destinations

sealed class Destinations(val route: String) {
    data object  Home : Destinations("home")
    data object  Notification : Destinations("notification")
    data object  Detail : Destinations("detail/{itemId}"){
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}