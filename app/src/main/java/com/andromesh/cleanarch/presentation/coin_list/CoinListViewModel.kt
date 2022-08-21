package com.andromesh.cleanarch.presentation.coin_list

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andromesh.cleanarch.common.Resource
import com.andromesh.cleanarch.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoin()
    }

    private fun getCoin() {
        getCoinUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinListState(coinList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        errorMessage = result.message ?: "An unexpected error accured..!"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}