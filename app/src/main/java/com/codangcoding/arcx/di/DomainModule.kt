package com.codangcoding.arcx.di

import com.codangcoding.arcx.domain.data.DefaultLeagueRepository
import com.codangcoding.arcx.domain.data.LeagueRepository
import com.codangcoding.arcx.domain.usecase.DefaultGetLeagueUseCase
import com.codangcoding.arcx.domain.usecase.GetLeagueUseCase
import com.codangcoding.arcx.presentation.LeagueListContract
import com.codangcoding.arcx.presentation.LeagueListPresenter
import com.codangcoding.arcx.util.DefaultAppSchedulers
import io.reactivex.disposables.CompositeDisposable

object DomainModule {

    private val repository: LeagueRepository by lazy {
        DefaultLeagueRepository(NetworkModule.leagueService)
    }

    private val useCase: GetLeagueUseCase by lazy {
        DefaultGetLeagueUseCase(repository)
    }

    val presenter: LeagueListContract.Presenter by lazy {
        LeagueListPresenter(useCase, CompositeDisposable(), DefaultAppSchedulers)
    }
}