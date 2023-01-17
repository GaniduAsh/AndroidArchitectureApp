package com.example.data.repository.impl

import com.example.data.repository.BeerRepository
import com.example.model.Beer
import com.example.network.api.BaseApi
import com.example.network.api.Result
import com.example.network.service.BeerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(private val beerService: BeerService) :
    BeerRepository {
    override  fun getBeerList(): Flow<Result<List<Beer>>> {
        return flow {
            emit(BaseApi.execute {
                beerService.getBeers()
            })
        }.flowOn(Dispatchers.IO)
    }
}