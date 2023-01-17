package com.example.beer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetBeerUseCase
import com.example.model.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.network.api.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(private val useCase: GetBeerUseCase) : ViewModel() {
    var beer by mutableStateOf<Beer?>(null)
    private set

    val beerUiState: StateFlow<BeerUiState> = getBeers()
        .stateIn(scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000) , initialValue = BeerUiState.Loading)

    private fun getBeers() : Flow<BeerUiState> {
            return useCase.invoke()
                .catch {
                    BeerUiState.Error
                }.map {
                     if(it is Result.Success) {
                         BeerUiState.Success(beers = it.data)
                     } else {
                         BeerUiState.Error
                    }
                }
        }

    fun addBeer(beer: Beer) {
        this.beer= beer
    }
}

sealed interface BeerUiState {
    data class Success(val beers: List<Beer>?) : BeerUiState
    object Error : BeerUiState
    object Loading: BeerUiState
}

