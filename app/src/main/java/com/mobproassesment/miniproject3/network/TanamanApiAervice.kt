package com.mobproassesment.miniproject3.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://asessment3-api-production-c0f9.up.railway.app/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TanamanApiAervice {
    @GET("tanaman")
    suspend fun getTanaman(): String
}

object TanamanApi{
    val service: TanamanApiAervice by lazy {
        retrofit.create(TanamanApiAervice::class.java)
    }
}
