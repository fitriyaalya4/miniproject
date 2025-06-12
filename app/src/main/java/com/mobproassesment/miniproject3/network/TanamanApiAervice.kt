package com.mobproassesment.miniproject3.network

import com.mobproassesment.miniproject3.model.Tanaman
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://asessment3-api-production-c0f9.up.railway.app/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TanamanApiAervice {
    @GET("tanaman")
    suspend fun getTanaman(): List<Tanaman>
}

object TanamanApi{
    val service: TanamanApiAervice by lazy {
        retrofit.create(TanamanApiAervice::class.java)
    }
}
