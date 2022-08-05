package com.andromesh.cleanarch.presentation.coin_details

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.andromesh.cleanarch.common.Constant
import com.andromesh.cleanarch.common.Resource
import com.andromesh.cleanarch.domain.use_cases.get_coins.GetCoinDetailUseCase
import com.andromesh.cleanarch.domain.use_cases.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constant.PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        getCoinDetailUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinDetailState(coinDetails = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        errorMessage = result.message ?: "An unexpected error accured..!"
                    )
                }
            }


        }
    }

}