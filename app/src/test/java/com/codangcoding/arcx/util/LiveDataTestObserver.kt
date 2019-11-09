package com.codangcoding.arcx.util

import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals

class LiveDataTestObserver<T> : Observer<T> {

    private val histories = mutableListOf<T>()

    override fun onChanged(t: T) {
        histories.add(t)
    }

    fun assertValueAt(index: Int, expectedValue: T) {
        assertEquals(expectedValue, histories.getOrNull(index))
    }

    fun assertValueAt(index: Int, block: (t: T?) -> Unit) {
        block(histories.getOrNull(index))
    }
}