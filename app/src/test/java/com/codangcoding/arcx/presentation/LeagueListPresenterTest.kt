package com.codangcoding.arcx.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codangcoding.arcx.domain.model.League
import com.codangcoding.arcx.domain.usecase.GetLeagueUseCase
import com.codangcoding.arcx.presentation.LeagueListContract.ViewState
import com.codangcoding.arcx.presentation.model.LeagueModel
import com.codangcoding.arcx.util.LiveDataTestObserver
import com.codangcoding.arcx.util.TestAppDispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class LeagueListPresenterTest {

    private val useCase = Mockito.mock(GetLeagueUseCase::class.java)
    private val jobs = Job()
    private val presenter = LeagueListPresenter(useCase, jobs, TestAppDispatchers)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun load_leagues_success() = runBlocking {
        val testObserver = LiveDataTestObserver<ViewState>()
        presenter.viewState.observeForever(testObserver)

        Mockito.`when`(useCase.execute())
            .thenReturn(
                listOf(
                    League("1", "EPL", "Soccer")
                )
            )

        presenter.loadLeagues()

        val expectedLeagues = listOf(
            LeagueModel("1", "EPL", "Soccer")
        )
        testObserver.assertValueAt(0, ViewState.Loading)
        testObserver.assertValueAt(1, ViewState.Success(expectedLeagues))
        assertEquals(1, jobs.children.count())
    }

    @Test
    fun should_emit_failure_when_error() = runBlocking {
        val testObserver = LiveDataTestObserver<ViewState>()
        presenter.viewState.observeForever(testObserver)

        Mockito.`when`(useCase.execute())
            .thenThrow(IllegalArgumentException("Ada error nih"))

        presenter.loadLeagues()

        testObserver.assertValueAt(0, ViewState.Loading)
        testObserver.assertValueAt(1) { viewState ->
            viewState?.let {
                assertEquals(
                    "Ada error nih",
                    (it as ViewState.Failure).throwable.message
                )
            }
        }
        assertEquals(1, jobs.children.count())
    }

    @Test
    fun should_dispose_disposables_on_destroy() = runBlocking {
        assertEquals(false, jobs.isCancelled)

        presenter.onDestroy()

        assertEquals(true, jobs.isCancelled)
    }
}