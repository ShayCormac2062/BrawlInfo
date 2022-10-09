package com.example.brawlinfo.domain.repository

import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.utils.Result

interface BrawlRepository {

    suspend fun getAllBrawlers(): Result<List<Brawler>?>
    suspend fun getBrawlerById(id: Int?): Result<Brawler?>
}