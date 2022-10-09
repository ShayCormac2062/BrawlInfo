package com.example.brawlinfo.domain.usecase

import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.domain.repository.BrawlRepository
import com.example.brawlinfo.utils.Result
import javax.inject.Inject

class GetBrawlerByIdUseCase @Inject constructor(
   private val repository: BrawlRepository
) {

    suspend operator fun invoke(id: Int?): Result<Brawler?> {
        return repository.getBrawlerById(id)
    }
}