package com.andromesh.cleanarch.domain.use_cases.get_coins

import com.andromesh.cleanarch.common.Resource
import com.andromesh.cleanarch.data.remote.dto.toCoin
import com.andromesh.cleanarch.domain.model.Coin
import com.andromesh.cleanarch.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {

        emit(Resource.Loading<List<Coin>>())

        try {
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach servers. Please check your internet connection"))

        }
    }

}