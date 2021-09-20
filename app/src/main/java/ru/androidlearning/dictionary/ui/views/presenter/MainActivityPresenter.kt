package ru.androidlearning.dictionary.ui.views.presenter

import ru.androidlearning.dictionary.ui.views.MainActivityMvpView

interface MainActivityPresenter {
    fun attachView(mainActivityMvpView: MainActivityMvpView)
    fun detachView()
    fun translate(word: String, language: String)
}
