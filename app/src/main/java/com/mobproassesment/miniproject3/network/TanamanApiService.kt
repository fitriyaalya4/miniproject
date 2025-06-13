package com.mobproassesment.miniproject3.network

import com.mobproassesment.miniproject3.model.OpStatus
import com.mobproassesment.miniproject3.model.Tanaman
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private const val BASE_URL = "https://asessment3-api-production-c0f9.up.railway.app/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TanamanApiService {
    @GET("tanaman")
    suspend fun getTanaman(
        @Header ("Authorization") userId: String
    ): List<Tanaman>

    @Multipart
    @POST("tanaman")
    suspend fun postTanaman(
        @Part("userId") userId: String,
        @Part("nama") nama: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus
}

object TanamanApi{
    val service: TanamanApiService by lazy {
        retrofit.create(TanamanApiService::class.java)
    }
    fun getTanamanUrl(gambar: String): String {
        return "https://asessment3-api-production-c0f9.up.railway.app/storage/$gambar"
    }
    enum class ApiStatus { LOADING, SUCCESS, FAILED }
}
