package com.example.brawlinfo.presentation.di

import com.example.brawlinfo.data.retrofit.Api
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    private val BASE_URL = "https://api.brawlapi.com/v1/"

    @Provides
    fun provideOkhttp(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    fun provideJsonConverterFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json.asConverterFactory(contentType)
    }

    @Provides
    @Named("retrofit")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        jsonConverter: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(jsonConverter)
        .build()

    @Provides
    @Named("api")
    fun provideAPi(
        @Named("retrofit") retrofit: Retrofit
    ): Api = retrofit.create(Api::class.java)
}