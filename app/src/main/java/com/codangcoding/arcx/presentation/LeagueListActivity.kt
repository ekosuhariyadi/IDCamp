package com.codangcoding.arcx.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codangcoding.arcx.R
import com.codangcoding.arcx.di.DomainModule
import com.codangcoding.arcx.presentation.LeagueListContract.ViewState
import kotlinx.android.synthetic.main.activity_league_list.*

class LeagueListActivity : AppCompatActivity(), LeagueListContract.View {

    lateinit var presenter: LeagueListContract.Presenter

    private lateinit var adapter: LeagueListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_list)


        adapter = LeagueListAdapter {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT)
                .show()
        }
        rv_league.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rv_league.adapter = adapter

        presenter.viewState.observe(this, Observer { viewState ->
            viewState?.let { render(it) }
        })

        presenter.loadLeagues()
    }

    override fun render(viewState: ViewState) {
        when (viewState) {
            is ViewState.Loading -> {
                pb_loading.visibility = View.VISIBLE
            }

            is ViewState.Success -> {
                pb_loading.visibility = View.GONE
                adapter.submitList(viewState.leagues)
            }

            is ViewState.Failure -> {
                pb_loading.visibility = View.GONE
                Toast.makeText(this, viewState.throwable.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object Injector {
        var injector = { target: LeagueListActivity ->
            target.presenter = DomainModule.presenter
        }

        fun inject(target: LeagueListActivity) {
            injector.invoke(target)
        }
    }
}
