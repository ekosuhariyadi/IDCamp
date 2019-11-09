package com.codangcoding.arcx.domain.usecase

import com.codangcoding.arcx.domain.data.LeagueRepository
import com.codangcoding.arcx.domain.model.League
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class DefaultGetLeagueUseCaseTest {

    private val repository = Mockito.mock(LeagueRepository::class.java)
    private val useCase = DefaultGetLeagueUseCase(repository)

    @Test
    fun should_return_list_of_league() {
        Mockito.`when`(repository.leagues())
            .thenReturn(
                Single.just(
                    listOf(
                        League("1", "EPL", "Soccer")
                    )
                )
            )

        val expectedValue = listOf(
            League("1", "EPL", "Soccer")
        )
        useCase.execute()
            .test()
            .assertComplete()
            .assertValue { it == expectedValue }
    }
}