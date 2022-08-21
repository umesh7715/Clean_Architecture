package com.andromesh.cleanarch.presentation.coin_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andromesh.cleanarch.presentation.Screen
import com.andromesh.cleanarch.presentation.coin_list.CoinListViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    coinListViewModel: CoinListViewModel = hiltViewModel()
) {

    val state = coinListViewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.coinList) { coin ->
                CoinlistItem(
                    coin = coin,
                    onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                    }
                )
            }
        }

        if (state.errorMessage.isNotBlank()) {
            Text(
                text = state.errorMessage,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)

            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

    }
}