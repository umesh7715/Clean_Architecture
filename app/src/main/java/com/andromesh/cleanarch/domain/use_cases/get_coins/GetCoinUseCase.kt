package com.andromesh.cleanarch.domain.use_cases.get_coins

import com.andromesh.cleanarch.common.Resource
import com.andromesh.cleanarch.data.remote.dto.toCoin
import com.andromesh.cleanarch.domain.model.Coin
import com.andromesh.cleanarch.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {

        emit(Resource.Loading())

        try {
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach servers. Please check your internet connection"))

        }
    }
}