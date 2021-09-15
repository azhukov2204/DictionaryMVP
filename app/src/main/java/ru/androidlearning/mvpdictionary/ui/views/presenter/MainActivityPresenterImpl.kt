package ru.androidlearning.mvpdictionary.ui.views.presenter

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import ru.androidlearning.mvpdictionary.schedulers.WorkSchedulers
import ru.androidlearning.mvpdictionary.ui.DictionaryPresentationData
import ru.androidlearning.mvpdictionary.ui.views.MainActivityView
import ru.androidlearning.mvpdictionary.ui.views.presenter.interactor.MainActivityInteractor

class MainActivityPresenterImpl(
    private val mainActivityInteractor: MainActivityInteractor,
    private val schedulers: WorkSchedulers
) : MainActivityPresenter {

    private var mainActivityView: MainActivityView? = null
    private val disposables = CompositeDisposable()
    private var currentData: DictionaryPresentationData? = null

    override fun attachView(mainActivityView: MainActivityView) {
        this.mainActivityView = mainActivityView
        currentData?.let { showResults(it) }
    }

    override fun detachView() {
        disposables.clear()
        mainActivityView = null
    }

    override fun translate(word: String, language: String) {
        if (word.isNotBlank()) {
            disposables +=
                mainActivityInteractor.translate(word, language)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .doOnSubscribe { mainActivityView?.showProgressBar() }
                    .subscribe(
                        ::showResults,
                        ::showError
                    )
        }
    }

    private fun showResults(dictionaryPresentationData: DictionaryPresentationData) {
        mainActivityView?.hideProgressBar()
        currentData = dictionaryPresentationData
        mainActivityView?.showTranslatedResult(dictionaryPresentationData)
        if (dictionaryPresentationData.translatedWords.isNullOrEmpty()) {
            mainActivityView?.showNoDataLabel()
        } else {
            mainActivityView?.hideNoDataLabel()
        }
    }

    private fun showError(e: Throwable) {
        mainActivityView?.hideProgressBar()
        e.printStackTrace()
        mainActivityView?.showError(e.message)
    }
}
