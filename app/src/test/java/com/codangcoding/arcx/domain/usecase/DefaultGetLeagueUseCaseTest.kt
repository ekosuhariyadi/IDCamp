package com.codangcoding.arcx.domain.usecase

import com.codangcoding.arcx.domain.data.LeagueRepository
import com.codangcoding.arcx.domain.model.League
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class DefaultGetLeagueUseCaseTest {

    private val repository = Mockito.mock(LeagueRepository::class.java)
    private val useCase = DefaultGetLeagueUseCase(repository)

    @Test
    fun should_return_list_of_league() = runBlocking {
        Mockito.`when`(repository.leagues())
            .thenReturn(
                listOf(
                    League("1", "EPL", "Soccer")
                )
            )

        val expectedValue = listOf(
            League("1", "EPL", "Soccer")
        )
        val result = useCase.execute()

        assertEquals(expectedValue, result)
    }
}