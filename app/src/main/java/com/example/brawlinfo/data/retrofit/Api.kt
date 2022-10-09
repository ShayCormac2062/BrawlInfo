package com.example.brawlinfo.data.retrofit

import com.example.brawlinfo.data.entity.BrawlList
import com.example.brawlinfo.data.entity.BrawlerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("brawlers")
    suspend fun getBrawlers(): BrawlList?

    @GET("brawlers/{brawlerId}")
    suspend fun getBrawlerById(@Path("brawlerId") id: Int?): BrawlerDto?

}