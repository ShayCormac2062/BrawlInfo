package com.example.brawlinfo.domain.usecase

import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.domain.repository.BrawlRepository
import com.example.brawlinfo.utils.Result
import javax.inject.Inject

class GetBrawlersUseCase @Inject constructor(
    private var repository: BrawlRepository
) {

    suspend operator fun invoke(): Result<List<Brawler>?> =
        repository.getAllBrawlers()

}