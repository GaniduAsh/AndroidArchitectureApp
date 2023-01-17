package com.example.androidbetaapp.navigation

sealed class Destination(val route: String) {
    object BeerListDestination: Destination(route = "beer_list_screen")
    object BeerDetailDestination: Destination(route = "beer_details_screen")
}