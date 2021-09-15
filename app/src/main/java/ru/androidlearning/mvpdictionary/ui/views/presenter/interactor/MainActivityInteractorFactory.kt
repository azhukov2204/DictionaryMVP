package ru.androidlearning.mvpdictionary.ui.views.presenter.interactor

import ru.androidlearning.mvpdictionary.data.repository.DictionaryRepositoryFactory

object MainActivityInteractorFactory {
    fun create(): MainActivityInteractor = MainActivityInteractorImpl(
        dictionaryRepository = DictionaryRepositoryFactory.create()
    )
}
