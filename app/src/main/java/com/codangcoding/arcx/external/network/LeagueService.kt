package com.codangcoding.arcx.external.network

import com.codangcoding.arcx.external.network.model.LeagueResponse
import io.reactivex.Single
import retrofit2.http.GET

interface LeagueService {

    @GET("all_leagues.php")
    fun allLeagues(): Single<LeagueResponse>
}