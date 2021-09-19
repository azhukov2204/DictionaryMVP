package ru.androidlearning.mvpdictionary.ui.views.presenter

import ru.androidlearning.mvpdictionary.ui.views.MainActivityMvpView

interface MainActivityPresenter {
    fun attachView(mainActivityMvpView: MainActivityMvpView)
    fun detachView()
    fun translate(word: String, language: String)
}
