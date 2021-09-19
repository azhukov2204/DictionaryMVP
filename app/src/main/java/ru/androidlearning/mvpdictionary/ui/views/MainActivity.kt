package ru.androidlearning.mvpdictionary.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.mvpdictionary.R
import ru.androidlearning.mvpdictionary.databinding.ActivityMainBinding
import ru.androidlearning.mvpdictionary.ui.DictionaryPresentationData
import ru.androidlearning.mvpdictionary.ui.views.list_adapter.TranslatedResultsListAdapter
import ru.androidlearning.mvpdictionary.ui.views.presenter.MainActivityPresenter
import ru.androidlearning.mvpdictionary.ui.views.presenter.MainActivityPresenterFactory

class MainActivity : AppCompatActivity(), MainActivityMvpView {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val mainActivityPresenter: MainActivityPresenter by lazy { MainActivityPresenterFactory.getPresenter() }
    private val translatedResultsListAdapter: TranslatedResultsListAdapter by lazy { TranslatedResultsListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        mainActivityPresenter.attachView(this)
    }

    override fun onStop() {
        mainActivityPresenter.detachView()
        super.onStop()
    }

    private fun initViews() {
        with(viewBinding) {
            enterWordTextInputLayout.setEndIconOnClickListener {
                mainActivityPresenter.translate(
                    word = enterWordEditText.text.toString(),
                    language = choseLanguageSpinner.selectedItem.toString()
                )
            }
            ArrayAdapter
                .createFromResource(this@MainActivity, R.array.translate_languages, R.layout.chose_language_spinner_item)
                .apply { setDropDownViewResource(android.R.layout.simple_dropdown_item_1line) }
                .let {
                    choseLanguageSpinner.adapter = it
                }
            translationResultsRecyclerView.adapter = translatedResultsListAdapter
        }
    }

    override fun showTranslatedResult(dictionaryPresentationData: DictionaryPresentationData) {
        with(viewBinding) {
            translatedWordTextView.text = dictionaryPresentationData.word
            translatedResultsListAdapter.submitList(dictionaryPresentationData.translatedWords)
        }
    }

    override fun showNoDataLabel() {
        viewBinding.noDataTextView.visibility = View.VISIBLE
    }

    override fun hideNoDataLabel() {
        viewBinding.noDataTextView.visibility = View.GONE
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, getString(R.string.error_message_prefix) + errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        viewBinding.includedLoadingSheet.loadingSheet.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        viewBinding.includedLoadingSheet.loadingSheet.visibility = View.GONE
    }
}
