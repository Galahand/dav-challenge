package com.example.platzi_challenge.network.repository

import com.example.platzi_challenge.data.Response
import com.example.platzi_challenge.network.api.BeerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val api: BeerService
) {
    suspend fun getBeers(page: Int = 1) = withContext(Dispatchers.IO) {
        try {
            val beers = api.getBeers(page, 20)
            Response.Success(beers)
        } catch (e: Throwable) {
            Response.Error(e)
        }
    }
}