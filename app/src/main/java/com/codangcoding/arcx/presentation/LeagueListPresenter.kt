package com.codangcoding.arcx.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codangcoding.arcx.domain.usecase.GetLeagueUseCase
import com.codangcoding.arcx.presentation.LeagueListContract.ViewState
import com.codangcoding.arcx.presentation.model.LeagueModelMapper
import com.codangcoding.arcx.util.AppDispatchers
import kotlinx.coroutines.*

class LeagueListPresenter(
    private val useCase: GetLeagueUseCase,
    private val jobs: Job,
    private val appDispatchers: AppDispatchers
) : LeagueListContract.Presenter {

    private val _viewState = MutableLiveData<ViewState>()
    private val presenterScope = CoroutineScope(SupervisorJob(jobs) + appDispatchers.ui())

    override fun loadLeagues() {
        presenterScope.launch {
            _viewState.value = ViewState.Loading

            val state: ViewState = withContext(appDispatchers.io()) {
                try {
                    val leaguesModel = useCase.execute()
                        .map(LeagueModelMapper::apply)
                    ViewState.Success(leaguesModel)
                } catch (ex: Throwable) {
                    ViewState.Failure(ex)
                }
            }
            _viewState.value = state
        }
    }

    override val viewState: LiveData<ViewState> =
        _viewState

    override fun onDestroy() {
        jobs.cancel()
    }
}