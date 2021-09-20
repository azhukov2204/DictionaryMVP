package ru.androidlearning.dictionary.ui.views.presenter

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import ru.androidlearning.dictionary.schedulers.WorkSchedulers
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.views.MainActivityMvpView
import ru.androidlearning.dictionary.ui.views.presenter.interactor.MainActivityInteractor

class MainActivityPresenterImpl(
    private val mainActivityInteractor: MainActivityInteractor,
    private val schedulers: WorkSchedulers
) : MainActivityPresenter {

    private var mainActivityMvpView: MainActivityMvpView? = null
    private val disposables = CompositeDisposable()
    private var currentData: DictionaryPresentationData? = null

    override fun attachView(mainActivityMvpView: MainActivityMvpView) {
        this.mainActivityMvpView = mainActivityMvpView
        currentData?.let { showResults(it) }
    }

    override fun detachView() {
        disposables.clear()
        mainActivityMvpView = null
    }

    override fun translate(word: String, language: String) {
        if (word.isNotBlank()) {
            disposables +=
                mainActivityInteractor.translate(word, language)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .doOnSubscribe { mainActivityMvpView?.showProgressBar() }
                    .subscribe(
                        ::showResults,
                        ::showError
                    )
        }
    }

    private fun showResults(dictionaryPresentationData: DictionaryPresentationData) {
        mainActivityMvpView?.hideProgressBar()
        currentData = dictionaryPresentationData
        mainActivityMvpView?.showTranslatedResult(dictionaryPresentationData)
        if (dictionaryPresentationData.translatedWords.isNullOrEmpty()) {
            mainActivityMvpView?.showNoDataLabel()
        } else {
            mainActivityMvpView?.hideNoDataLabel()
        }
    }

    private fun showError(e: Throwable) {
        mainActivityMvpView?.hideProgressBar()
        e.printStackTrace()
        mainActivityMvpView?.showError(e.message)
    }
}
