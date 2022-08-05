package com.andromesh.cleanarch.domain.model

import com.andromesh.cleanarch.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val description: String,
    val name: String,
    val symbol: String,
    val isActive: Boolean,
    val rank: Int,
    val tags: List<String>,
    val team: List<TeamMember>

)
