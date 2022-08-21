package com.andromesh.cleanarch.presentation.coin_details.components

import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andromesh.cleanarch.presentation.Screen
import com.andromesh.cleanarch.presentation.coin_details.CoinDetailViewModel
import com.andromesh.cleanarch.presentation.coin_list.CoinListViewModel
import com.andromesh.cleanarch.presentation.coin_list.components.CoinlistItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    coinListViewModel: CoinDetailViewModel = hiltViewModel()
) {

    val state = coinListViewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        state.coinDetails?.let { coin ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h4,
                            modifier = Modifier.weight(8f)
                        )

                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f),
                            textAlign = TextAlign.End,
                            fontStyle = FontStyle.Italic
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = coin.description,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.body1
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h4
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Team members",
                        style = MaterialTheme.typography.h4
                    )

                }

                items(coin.team) { teamMember ->
                    TeamMemberListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }


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