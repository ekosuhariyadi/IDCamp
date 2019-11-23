package com.codangcoding.arcx.domain.data

import com.codangcoding.arcx.domain.model.League
import com.codangcoding.arcx.domain.model.LeagueMapper
import com.codangcoding.arcx.external.network.LeagueService

interface LeagueRepository {

    suspend fun leagues(): List<League>
}

class DefaultLeagueRepository(
    private val leagueService: LeagueService
) : LeagueRepository {

    override suspend fun leagues(): List<League> {
        return leagueService.allLeagues()
            .leagues
            .map(LeagueMapper::apply)
            .filter { it.sport == "Soccer" }
            .toList()
    }

}