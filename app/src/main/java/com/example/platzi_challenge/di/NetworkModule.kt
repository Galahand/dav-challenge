package com.example.platzi_challenge.di

import com.example.platzi_challenge.network.api.BeerService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesMainService(moshi: Moshi): BeerService = BeerService.create(moshi)
}

@InstallIn(SingletonComponent::class)
@Module
class AdapterModule {
    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}