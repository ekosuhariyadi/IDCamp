package com.codangcoding.arcx.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object TestAppSchedulers : AppSchedulers {

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }
}