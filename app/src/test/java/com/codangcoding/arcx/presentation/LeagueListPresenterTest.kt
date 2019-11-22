package com.codangcoding.arcx.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codangcoding.arcx.domain.model.League
import com.codangcoding.arcx.domain.usecase.GetLeagueUseCase
import com.codangcoding.arcx.presentation.LeagueListContract.ViewState
import com.codangcoding.arcx.presentation.model.LeagueModel
import com.codangcoding.arcx.util.LiveDataTestObserver
import com.codangcoding.arcx.util.TestAppSchedulers
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class LeagueListPresenterTest {

    private val useCase = Mockito.mock(GetLeagueUseCase::class.java)
    private val disposables = CompositeDisposable()
    private val presenter = LeagueListPresenter(useCase, disposables, TestAppSchedulers)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun load_leagues_success() {
        val testObserver = LiveDataTestObserver<ViewState>()
        presenter.viewState.observeForever(testObserver)

        Mockito.`when`(useCase.execute())
            .thenReturn(
                Single.just(
                    listOf(
                        League("1", "EPL", "Soccer")
                    )
                )
            )

        presenter.loadLeagues()

        val expectedLeagues = listOf(
            LeagueModel("1", "EPL", "Soccer")
        )
        testObserver.assertValueAt(0, ViewState.Loading)
        testObserver.assertValueAt(1, ViewState.Success(expectedLeagues))
        assertEquals(1, disposables.size())
    }

    @Test
    fun should_emit_failure_when_error() {
        val testObserver = LiveDataTestObserver<ViewState>()
        presenter.viewState.observeForever(testObserver)

        Mockito.`when`(useCase.execute())
            .thenReturn(Single.error(IllegalArgumentException("Ada error nih")))

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
        assertEquals(1, disposables.size())
    }

    @Test
    fun should_dispose_disposables_on_destroy() {
        assertEquals(false, disposables.isDisposed)

        presenter.onDestroy()

        assertEquals(true, disposables.isDisposed)
    }
}