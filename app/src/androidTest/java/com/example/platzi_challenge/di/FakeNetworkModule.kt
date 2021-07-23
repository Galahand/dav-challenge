package com.example.platzi_challenge.di

import com.example.platzi_challenge.network.api.BeerService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class],
)
class FakeNetworkModule {

    @ExperimentalStdlibApi
    @Singleton
    @Provides
    fun providesBeerService() = mockk<BeerService>().apply {
        coEvery { getBeers(any(), any()) } returns emptyList()
    }
}