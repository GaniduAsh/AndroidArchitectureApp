package com.example.data.repository.di

import com.example.data.repository.BeerRepository
import com.example.data.repository.impl.BeerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindBeerRepository(beerRepositoryImpl: BeerRepositoryImpl): BeerRepository;
}