package ru.androidlearning.model.stopwatch.holder

import ru.androidlearning.model.stopwatch.StopwatchState

interface StopwatchStateHolder {
    fun getCurrentState(): StopwatchState
    fun start()
    fun pause()
    fun stop()
    fun getStringTimeRepresentation(): String
}
