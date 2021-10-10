package ru.androidlearning.presentation.interactor

import ru.androidlearning.core.DictionaryPresentationData
import ru.androidlearning.model.repository.DictionaryRepository
import ru.androidlearning.model.stopwatch.StopwatchOrchestrator

class InteractorImpl(
    private val dictionaryRepository: DictionaryRepository,
    private vararg val stopwatchOrchestrators: StopwatchOrchestrator,
) : Interactor {

    override suspend fun getHistory(): DictionaryPresentationData =
        dictionaryRepository
            .getHistory()
            .let(DictionaryPresentationData.Mapper::map)

    override suspend fun search(word: String, isOnline: Boolean): DictionaryPresentationData =
        dictionaryRepository.search(word, isOnline)
            .let(DictionaryPresentationData.Mapper::map)

    override fun getStopwatchOrchestrators(): Array<out StopwatchOrchestrator> =
        stopwatchOrchestrators
}
