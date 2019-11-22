package com.codangcoding.arcx.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codangcoding.arcx.domain.usecase.GetLeagueUseCase
import com.codangcoding.arcx.presentation.LeagueListContract.ViewState
import com.codangcoding.arcx.presentation.model.LeagueModelMapper
import com.codangcoding.arcx.util.AppSchedulers
import io.reactivex.disposables.CompositeDisposable

class LeagueListPresenter(
    private val useCase: GetLeagueUseCase,
    private val disposables: CompositeDisposable,
    private val appSchedulers: AppSchedulers
) : LeagueListContract.Presenter {

    private val _viewState = MutableLiveData<ViewState>()

    override fun loadLeagues() {
        useCase.execute()
            .subscribeOn(appSchedulers.io())
            .map { leagues ->
                ViewState.Success(
                    leagues.map(LeagueModelMapper::apply)
                ) as ViewState
            }
            .toObservable()
            .onErrorReturn { ViewState.Failure(it) }
            .startWith(ViewState.Loading)
            .observeOn(appSchedulers.ui())
            .subscribe {
                _viewState.value = it
            }
            .let { disposables.add(it) }
    }

    override val viewState: LiveData<ViewState> =
        _viewState

    override fun onDestroy() {
        disposables.dispose()
    }
}