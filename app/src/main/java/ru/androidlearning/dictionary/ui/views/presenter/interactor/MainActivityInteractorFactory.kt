package ru.androidlearning.dictionary.ui.views.presenter.interactor

import ru.androidlearning.dictionary.data.repository.DictionaryRepositoryFactory

object MainActivityInteractorFactory {
    fun create(): MainActivityInteractor = MainActivityInteractorImpl(
        dictionaryRepository = DictionaryRepositoryFactory.create()
    )
}
