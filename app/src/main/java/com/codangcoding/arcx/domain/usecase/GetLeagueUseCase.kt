package com.codangcoding.arcx.domain.usecase

import com.codangcoding.arcx.domain.data.LeagueRepository
import com.codangcoding.arcx.domain.model.League

interface GetLeagueUseCase {

    suspend fun execute(): List<League>
}

class DefaultGetLeagueUseCase(
    private val repository: LeagueRepository
) : GetLeagueUseCase {

    override suspend fun execute(): List<League> {
        return repository.leagues()
    }
}