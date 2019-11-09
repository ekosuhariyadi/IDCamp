package com.codangcoding.arcx.presentation

import androidx.lifecycle.LiveData
import com.codangcoding.arcx.presentation.model.LeagueModel

interface LeagueListContract {

    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val leagues: List<LeagueModel>) : ViewState()
        data class Failure(val throwable: Throwable) : ViewState()
    }

    interface Presenter {

        fun loadLeagues()

        val viewState: LiveData<ViewState>
    }

    interface View {

        fun render(viewState: ViewState)
    }
}