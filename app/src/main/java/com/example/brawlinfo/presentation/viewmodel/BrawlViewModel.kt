package com.example.brawlinfo.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brawlinfo.domain.model.Brawler
import com.example.brawlinfo.domain.usecase.GetBrawlerByIdUseCase
import com.example.brawlinfo.domain.usecase.GetBrawlersUseCase
import com.example.brawlinfo.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrawlViewModel @Inject constructor(
    private val getBrawlerByIdUseCase: GetBrawlerByIdUseCase,
    private val getBrawlersUseCase: GetBrawlersUseCase
) : ViewModel() {

    var brawlersList = mutableStateOf<List<Brawler>?>(null)
    var brawlerById = mutableStateOf<Brawler?>(null)

    fun getBrawlers() {
        viewModelScope.launch {
            when (val result = getBrawlersUseCase()) {
                is Result.Success -> {
                    brawlersList.value = result.data
                }
                is Result.Error -> {
                    brawlersList.value = emptyList()
                }
            }
        }
    }

    fun getBrawlerById(id: Int?) {
        viewModelScope.launch {
            when (val result = getBrawlerByIdUseCase(id)) {
                is Result.Success -> {
                    brawlerById.value = result.data
                }
                is Result.Error -> {
                    brawlerById.value = null
                }
            }
        }
    }

    fun onBackPressed() {
        brawlerById.value = null
    }

}