package com.gtdan.marvelucm.network

import com.gtdan.marvelucm.network.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://ucm-api.herokuapp.com"

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("api/v1/movies")
    suspend fun getMovies(): ItemResponseModel

    @GET("api/v1/series")
    suspend fun getSeries(): ItemResponseModel

    @GET("api/v1/upcoming")
    suspend fun getUpcoming(): ItemUpcominResponseModel

    @GET("api/v1/item/{type}/{id}")
    suspend fun getItem(@Path("type") itemType: String, @Path("id") itemId: String): SpecificItemResponseModel
}

object MarvelApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}