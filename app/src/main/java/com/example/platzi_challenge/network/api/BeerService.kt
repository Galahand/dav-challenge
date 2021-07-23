package com.example.platzi_challenge.network.api

import com.example.platzi_challenge.data.Beer
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerService {
    @GET("beers")
    suspend fun  getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<Beer>

    companion object {
        private const val BASE_URL = "https://api.punkapi.com/v2/"

        fun create(moshi: Moshi): BeerService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val moshiFactory = MoshiConverterFactory.create(moshi)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(moshiFactory)
                .build()
                .create(BeerService::class.java)
        }
    }
}