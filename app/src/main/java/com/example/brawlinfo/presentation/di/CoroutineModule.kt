package com.example.brawlinfo.presentation.di

import com.example.brawlinfo.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    fun provideScope(): DispatcherProvider = DispatcherProvider()

}