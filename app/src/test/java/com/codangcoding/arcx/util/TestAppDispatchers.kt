package com.codangcoding.arcx.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object TestAppDispatchers : AppDispatchers {

    override fun io(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

    override fun computation(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}