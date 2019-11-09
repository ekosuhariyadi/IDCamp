package com.codangcoding.arcx.domain.data

import com.codangcoding.arcx.domain.model.League
import com.codangcoding.arcx.external.network.LeagueService
import com.codangcoding.arcx.external.network.model.LeagueDTO
import com.codangcoding.arcx.external.network.model.LeagueResponse
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class DefaultLeagueRepositoryTest {

    private val leagueService = Mockito.mock(LeagueService::class.java)
    private val repository = DefaultLeagueRepository(leagueService)

    @Test
    fun should_return_soccer_league_only() {
        Mockito.`when`(leagueService.allLeagues())
            .thenReturn(
                Single.just(
                    LeagueResponse(
                        listOf(
                            LeagueDTO("1", "EPL", "Soccer"),
                            LeagueDTO("2", "Major League", "Baseball")
                        )
                    )
                )
            )

        val expectedValue = listOf(
            League("1", "EPL", "Soccer")
        )
        repository.leagues()
            .test()
            .assertComplete()
            .assertValue { it == expectedValue }
    }
}