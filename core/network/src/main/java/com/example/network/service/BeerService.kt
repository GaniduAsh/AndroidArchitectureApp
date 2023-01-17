package com.example.network.service

import com.example.model.Beer
import retrofit2.Response
import retrofit2.http.GET

interface BeerService {
    /**
     * Fetch list of list beers from remote
     */
    @GET(value =  "v2/beers")
    suspend fun getBeers(): Response<List<Beer>>
}