package com.codangcoding.arcx.presentation.model

import com.codangcoding.arcx.domain.model.League

object LeagueModelMapper {

    fun apply(league: League): LeagueModel =
        LeagueModel(league.id, league.name, league.sport)
}
