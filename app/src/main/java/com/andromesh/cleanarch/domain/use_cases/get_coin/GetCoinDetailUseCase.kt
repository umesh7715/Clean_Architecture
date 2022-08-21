package com.andromesh.cleanarch.domain.use_cases.get_coin

import com.andromesh.cleanarch.common.Resource
import com.andromesh.cleanarch.data.remote.dto.toCoinDetail
import com.andromesh.cleanarch.domain.model.CoinDetail
import com.andromesh.cleanarch.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {

        emit(Resource.Loading<CoinDetail>())
        try {
            val coinDetail = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coinDetail))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach servers. Please check you internet connection"))
        }
    }
}