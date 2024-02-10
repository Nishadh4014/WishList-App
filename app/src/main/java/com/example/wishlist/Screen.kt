package com.example.wishlist


// no one can inherit this
sealed class Screen(val route: String) {

    object HomeScreen: Screen("home_screen")
    object AddScreen: Screen("add_screen")

}