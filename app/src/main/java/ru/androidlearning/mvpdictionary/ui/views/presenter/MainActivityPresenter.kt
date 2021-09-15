package ru.androidlearning.mvpdictionary.ui.views.presenter

import ru.androidlearning.mvpdictionary.ui.views.MainActivityView

interface MainActivityPresenter {
    fun attachView(mainActivityView: MainActivityView)
    fun detachView()
    fun translate(word: String, language: String)
}
