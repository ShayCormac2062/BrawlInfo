package com.example.brawlinfo.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProvider {

    val Main: CoroutineDispatcher = Dispatchers.Main
    val Default: CoroutineDispatcher = Dispatchers.Default
    val IO: CoroutineDispatcher = Dispatchers.IO

}