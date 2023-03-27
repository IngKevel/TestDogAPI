package com.example.dogapichallengue.ui.dogpage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogapichallengue.domain.contracts.IDogRepository
import com.example.dogapichallengue.domain.models.DogModel
import com.example.dogapichallengue.domain.models.core.RepositoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kevel on 3/23/2023.
 */

@HiltViewModel
class DogPageViewModel @Inject constructor(
    private val dogRepository: IDogRepository
): ViewModel() {

    private val _dogDetail = mutableStateOf(DogModel("", ""))
    val dogDetail: State<DogModel>
        get() = _dogDetail

    private val _dogPageViewState = mutableStateOf<DogPageViewState>(DogPageViewState.Loading)
    val dogPageViewState: State<DogPageViewState>
        get() = _dogPageViewState

    fun getNewDogByDbn() {
        viewModelScope.launch {
            _dogPageViewState.value = DogPageViewState.Loading
            when(val result = dogRepository.getDog()) {
                is RepositoryResponse.Error -> {
                    _dogPageViewState.value = DogPageViewState.Error(result.message)
                }
                is RepositoryResponse.Success -> {
                    _dogDetail.value = result.data
                    _dogPageViewState.value = DogPageViewState.Idle
                }
            }
        }
    }
}