package com.codangcoding.arcx.presentation

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.codangcoding.arcx.presentation.model.LeagueModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class LeagueListActivityTest {

    @get:Rule
    val rule = ActivityTestRule(LeagueListActivity::class.java, false, false)

    private val mockPresenter = Mockito.mock(LeagueListContract.Presenter::class.java)

    @Before
    fun setup() {
        LeagueListActivity.injector = { target: LeagueListActivity ->
            target.presenter = mockPresenter
        }

        Mockito.`when`(mockPresenter.viewState)
            .thenReturn(MutableLiveData())

        rule.launchActivity(null)
    }

    @Test
    fun should_load_league_on_start() {
        Mockito.verify(mockPresenter).loadLeagues()
    }

    @Test
    fun should_render_on_success_state() {
        render(
            LeagueListContract.ViewState.Success(
                listOf(
                    LeagueModel("1", "EPL", "Soccer")
                )
            )
        )

        onView(withText("EPL")).check(matches(isDisplayed()))
    }

    private fun render(viewState: LeagueListContract.ViewState) {
        rule.runOnUiThread {
            rule.activity.render(viewState)
        }
    }
}