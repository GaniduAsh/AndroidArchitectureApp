package com.example.beer

import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.model.Beer

@Composable
fun BeerDetailsRoute(viewModel: BeerListViewModel) {
    val beer = viewModel.beer
    if(beer != null) {
        BeerDetailsScreen(beer = beer)
    }
}

@Composable
fun BeerDetailsScreen(modifier: Modifier = Modifier, beer: Beer?) {
            Column(modifier) {
                if(beer != null) {
                    Text(text = beer.name)
                    Text(text = beer.description)
                }
            }

}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_TYPE_NORMAL)
@Composable
fun PreviewBeerDetailsScreen() {
    //BeerDetailsScreen(beer = Beer(1,"Name", tagline = "TagLine", imageUrl = "" , description = "Description", abv =  "1.5", ibu =  "2.5"))
}
