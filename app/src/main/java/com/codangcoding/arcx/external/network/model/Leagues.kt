package com.codangcoding.arcx.external.network.model

import com.fasterxml.jackson.annotation.JsonProperty

data class LeagueDTO(
    @JsonProperty("idLeague") val id: String,
    @JsonProperty("strLeague") val name: String,
    @JsonProperty("strSport") val sport: String
)

data class LeagueResponse(
    val leagues: List<LeagueDTO>
)