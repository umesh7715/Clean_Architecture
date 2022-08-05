package com.andromesh.cleanarch.domain.repository

import com.andromesh.cleanarch.data.remote.dto.CoinDetailDto
import com.andromesh.cleanarch.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}