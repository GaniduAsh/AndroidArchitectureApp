package com.example.domain

import com.example.data.repository.BeerRepository
import com.example.model.Beer
import com.example.network.api.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(private  val beerRepository: BeerRepository) {
    fun invoke() : Flow<Result<List<Beer>>> {
        return beerRepository.getBeerList()
    }
}