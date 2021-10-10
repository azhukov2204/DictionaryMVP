package ru.androidlearning.presentation.interactor

import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.model.repository.DictionaryRepository
import ru.androidlearning.model.stopwatch.StopwatchOrchestrator

class InteractorImpl(
    private val dictionaryRepository: DictionaryRepository,
    private vararg val stopwatchOrchestrators: StopwatchOrchestrator,
) : Interactor {

    override suspend fun getHistory(): DictionaryPresentationDataModel =
        dictionaryRepository
            .getHistory()
            .let(DictionaryPresentationDataModel.Mapper::map)

    override suspend fun search(word: String, isOnline: Boolean): DictionaryPresentationDataModel =
        dictionaryRepository.search(word, isOnline)
            .let(DictionaryPresentationDataModel.Mapper::map)

    override fun getStopwatchOrchestrators(): Array<out StopwatchOrchestrator> =
        stopwatchOrchestrators
}
