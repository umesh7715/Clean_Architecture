package com.andromesh.cleanarch.data.remote

import com.andromesh.cleanarch.data.remote.dto.CoinDetailDto
import com.andromesh.cleanarch.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaAPI {

    @GET("/v1/coins")
    suspend fun getCoinList(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}