package ru.androidlearning.model.stopwatch

import kotlinx.coroutines.flow.StateFlow

interface StopwatchOrchestrator {
    val ticker: StateFlow<String>
    fun start()
    fun pause()
    fun stop()
}
