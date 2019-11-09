package com.codangcoding.arcx.domain.data

import com.codangcoding.arcx.domain.model.League
import com.codangcoding.arcx.domain.model.LeagueMapper
import com.codangcoding.arcx.external.network.LeagueService
import io.reactivex.Observable
import io.reactivex.Single

interface LeagueRepository {

    fun leagues(): Single<List<League>>
}

class DefaultLeagueRepository(
    private val leagueService: LeagueService
) : LeagueRepository {

    override fun leagues(): Single<List<League>> {
        return leagueService.allLeagues()
            .flatMapObservable { Observable.fromIterable(it.leagues) }
            .map(LeagueMapper::apply)
            .filter { it.sport == "Soccer" }
            .toList()
    }

}