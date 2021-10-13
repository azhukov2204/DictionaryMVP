package ru.androidlearning.presentation.interactor

import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.model.stopwatch.StopwatchOrchestrator

interface Interactor {
    suspend fun getHistory(): DictionaryPresentationDataModel
    suspend fun search(word: String, isOnline: Boolean): DictionaryPresentationDataModel
    fun getStopwatchOrchestrators(): Array<out StopwatchOrchestrator>
}
