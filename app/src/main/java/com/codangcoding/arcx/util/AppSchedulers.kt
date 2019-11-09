package com.codangcoding.arcx.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface AppSchedulers {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler
}

object DefaultAppSchedulers : AppSchedulers {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

}