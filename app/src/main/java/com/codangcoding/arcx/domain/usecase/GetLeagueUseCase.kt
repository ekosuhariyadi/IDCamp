package com.codangcoding.arcx.domain.usecase

import com.codangcoding.arcx.domain.data.LeagueRepository
import com.codangcoding.arcx.domain.model.League
import io.reactivex.Single

interface GetLeagueUseCase {

    fun execute(): Single<List<League>>
}

class DefaultGetLeagueUseCase(
    private val repository: LeagueRepository
) : GetLeagueUseCase {

    override fun execute(): Single<List<League>> {
        return repository.leagues()
    }
}