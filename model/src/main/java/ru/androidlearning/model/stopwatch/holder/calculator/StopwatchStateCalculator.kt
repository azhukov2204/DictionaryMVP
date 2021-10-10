package ru.androidlearning.model.stopwatch.holder.calculator

import ru.androidlearning.model.stopwatch.StopwatchState

interface StopwatchStateCalculator {
    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running
    fun calculatePausedState(oldState: StopwatchState): StopwatchState.Paused
    fun calculateElapsedTime(state: StopwatchState.Running): Long
}
