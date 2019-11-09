package com.codangcoding.arcx.domain.model

import com.codangcoding.arcx.external.network.model.LeagueDTO

object LeagueMapper {

    fun apply(leagueDTO: LeagueDTO): League =
        League(leagueDTO.id, leagueDTO.name, leagueDTO.sport)
}