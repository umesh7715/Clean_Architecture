package com.andromesh.cleanarch.presentation.coin_list

import com.andromesh.cleanarch.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val errorMessage: String = ""
)
