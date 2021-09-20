package ru.androidlearning.dictionary.ui.views.presenter

import ru.androidlearning.dictionary.schedulers.WorkSchedulersFactory
import ru.androidlearning.dictionary.ui.views.presenter.interactor.MainActivityInteractorFactory

object MainActivityPresenterFactory {
    private val mainActivityPresenter: MainActivityPresenter by lazy {
        MainActivityPresenterImpl(
            mainActivityInteractor = MainActivityInteractorFactory.create(),
            schedulers = WorkSchedulersFactory.create()
        )
    }

    fun getPresenter(): MainActivityPresenter = mainActivityPresenter
}
