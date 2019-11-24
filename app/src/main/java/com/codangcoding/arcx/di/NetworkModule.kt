package com.codangcoding.arcx.di

import com.codangcoding.arcx.BuildConfig
import com.codangcoding.arcx.external.network.LeagueService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object NetworkModule {

    private val objectMapper by lazy {
        ObjectMapper()
            .registerKotlinModule()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/${BuildConfig.API_KEY}/")
            .client(OkHttpClient())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
    }

    val leagueService: LeagueService by lazy {
        retrofit.create(LeagueService::class.java)
    }
}