package com.example.brawlinfo.presentation.di

import com.example.brawlinfo.data.retrofit.Api
import com.example.brawlinfo.data.repository.BrawlRepositoryImpl
import com.example.brawlinfo.domain.repository.BrawlRepository
import com.example.brawlinfo.domain.usecase.GetBrawlerByIdUseCase
import com.example.brawlinfo.domain.usecase.GetBrawlersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBrawlerRepository(
        @Named("api") api: Api
    ): BrawlRepository = BrawlRepositoryImpl(api)

    @Provides
    fun provideGetBrawlersUseCase(
        repository: BrawlRepository
    ): GetBrawlersUseCase = GetBrawlersUseCase(repository)

    @Provides
    fun provideGetBrawlerByIdUseCase(
        repository: BrawlRepository
    ): GetBrawlerByIdUseCase = GetBrawlerByIdUseCase(repository)

}