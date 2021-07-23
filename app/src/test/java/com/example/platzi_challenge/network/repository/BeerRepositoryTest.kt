package com.example.platzi_challenge.network.repository

import com.example.platzi_challenge.data.Response
import com.example.platzi_challenge.network.api.BeerService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BeerRepositoryTest {

    private val fakeService = mockk<BeerService>()
    private val classUnderTest = BeerRepository(fakeService)

    @Test
    fun `get beers success`() = runBlocking {
        coEvery { fakeService.getBeers(any(), any()) } returns listOf()
        val result = classUnderTest.getBeers(1)
        assert(result is Response.Success && result.data.isEmpty())
    }

    @Test
    fun `get beers failure`() = runBlocking {
        coEvery { fakeService.getBeers(any(), any()) } throws Exception()
        val result = classUnderTest.getBeers(1)
        assert(result is Response.Error)
    }
}