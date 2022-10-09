package com.example.brawlinfo.data.repository

import android.util.Log
import com.example.brawlinfo.data.retrofit.Api
import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.domain.repository.BrawlRepository
import com.example.brawlinfo.utils.Result
import javax.inject.Inject

class BrawlRepositoryImpl @Inject constructor(
    private val api: Api
) : BrawlRepository {

    override suspend fun getAllBrawlers(): Result<List<Brawler>?> {
        val result = api.getBrawlers()
        return try {
            Result.Success(result
                ?.list
                ?.map {
                    it.toBrawler()
                })
        } catch (ex: Exception) {
            Result.Error(ex.message.toString())
        }
    }

    override suspend fun getBrawlerById(id: Int?): Result<Brawler?> {
        return try {
            val result = api.getBrawlerById(id)
            Result.Success(result?.toBrawler())
        } catch (ex: Exception) {
            Log.e("FUCK", ex.message.toString())
            Result.Error(ex.message.toString())
        }
    }
}