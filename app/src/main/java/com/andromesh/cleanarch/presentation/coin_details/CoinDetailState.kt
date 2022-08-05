package com.andromesh.cleanarch.presentation.coin_details

import com.andromesh.cleanarch.domain.model.Coin
import com.andromesh.cleanarch.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetail? = null,
    val errorMessage: String = ""
)
