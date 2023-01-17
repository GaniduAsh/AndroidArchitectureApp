package com.example.androidbetaapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.beer.BeerDetailsRoute
import com.example.beer.BeerListRoute
import com.example.beer.BeerListViewModel

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String, viewModel: BeerListViewModel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Destination.BeerListDestination.route) {
            BeerListRoute(navController= navController, viewModel = viewModel)
        }
        composable(route = Destination.BeerDetailDestination.route) { 
            BeerDetailsRoute(viewModel = viewModel)
        }   
    }
}