package com.andromesh.cleanarch.data.repository

import com.andromesh.cleanarch.data.remote.CoinPaprikaAPI
import com.andromesh.cleanarch.data.remote.dto.CoinDetailDto
import com.andromesh.cleanarch.data.remote.dto.CoinDto
import com.andromesh.cleanarch.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaAPI
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoinList()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}