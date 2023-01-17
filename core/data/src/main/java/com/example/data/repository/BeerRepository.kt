package com.example.data.repository

import com.example.model.Beer
import com.example.network.api.Result
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
     fun getBeerList(): Flow<Result<List<Beer>>>
}