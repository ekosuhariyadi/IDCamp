package com.codangcoding.arcx.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers {

    fun io(): CoroutineDispatcher

    fun ui(): CoroutineDispatcher

    fun computation(): CoroutineDispatcher
}

object DefaultAppDispatchers : AppDispatchers {

    override fun io(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun computation(): CoroutineDispatcher {
        return Dispatchers.Default
    }

}