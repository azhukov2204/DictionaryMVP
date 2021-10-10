package ru.androidlearning.presentation.interactor

import ru.androidlearning.core.DictionaryPresentationData
import ru.androidlearning.model.stopwatch.StopwatchOrchestrator

interface Interactor {
    suspend fun getHistory(): DictionaryPresentationData
    suspend fun search(word: String, isOnline: Boolean): DictionaryPresentationData
    fun getStopwatchOrchestrators(): Array<out StopwatchOrchestrator>
}
