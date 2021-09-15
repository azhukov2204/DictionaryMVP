package ru.androidlearning.mvpdictionary.ui.views.presenter

import ru.androidlearning.mvpdictionary.schedulers.WorkSchedulersFactory
import ru.androidlearning.mvpdictionary.ui.views.presenter.interactor.MainActivityInteractorFactory

object MainActivityPresenterFactory {
    private val mainActivityPresenter: MainActivityPresenter by lazy {
        MainActivityPresenterImpl(
            mainActivityInteractor = MainActivityInteractorFactory.create(),
            schedulers = WorkSchedulersFactory.create()
        )
    }

    fun getPresenter(): MainActivityPresenter = mainActivityPresenter
}
