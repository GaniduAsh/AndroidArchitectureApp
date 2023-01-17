package com.example.beer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.model.Beer

@Composable
fun BeerListRoute(navController: NavController, viewModel: BeerListViewModel) {
    val beerUiState: BeerUiState by viewModel.beerUiState.collectAsStateWithLifecycle()
    BeerListScreen(beerUiState = beerUiState, navController = navController, viewModel = viewModel)
}

@Composable
fun BeerListScreen(modifier: Modifier = Modifier, beerUiState: BeerUiState, navController: NavController, viewModel: BeerListViewModel) {
    BeerItemList(modifier = modifier, beerUiState = beerUiState, navController = navController, viewModel = viewModel)
}

@Composable
fun BeerItemList(modifier: Modifier = Modifier, beerUiState: BeerUiState, navController: NavController, viewModel: BeerListViewModel) {
    when(beerUiState) {
        BeerUiState.Loading -> {
        }
        is BeerUiState.Success -> {
            LazyColumn (modifier = modifier) {
                beerUiState.beers?.let { beerItem(it, navController, viewModel) }
            }
        }
        is BeerUiState.Error -> {
        }
    }
}

fun LazyListScope.beerItem(items : List<Beer>, navController: NavController, viewModel: BeerListViewModel) = items (items = items, itemContent =  {
    item ->
    ItemView(beer = item, navController = navController, viewModel = viewModel)
    Divider(color = Color.Black)
})

@Composable
fun ItemView(modifier: Modifier = Modifier, beer: Beer, navController: NavController, viewModel: BeerListViewModel) {
  Row(
      modifier
          .fillMaxWidth()
          .clickable {
              viewModel.addBeer(beer)
              navController.navigate(route = "beer_details_screen")
          }) {
      Image(
          painter = rememberAsyncImagePainter(beer.imageUrl),
          contentDescription = "Image",
          contentScale = ContentScale.Fit,
          modifier = Modifier
              .size(150.dp)
      )
      Column {
          beer.tagline?.let { Text(text = it) }
          beer.name?.let { Text(text = it) }
      }
  }
}


@Preview(showBackground = true)
@Composable
fun PreviewBeerItemView() {
    ItemView(beer = Beer(1,"Name", tagline = "TagLine", imageUrl = "" ,
        description = "Description", abv =  "1.5", ibu =  "2.5"), navController = rememberNavController(
    ), viewModel = hiltViewModel
        ())
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
internal fun PreviewBeerListScreen() {
    BeerListScreen(navController = rememberNavController(), beerUiState = BeerUiState.Loading, viewModel = hiltViewModel())
}
