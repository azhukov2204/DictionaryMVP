package ru.androidlearning.dictionary.ui.activity

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.dictionary.R
import ru.androidlearning.dictionary.databinding.ActivityMainBinding
import ru.androidlearning.dictionary.di.modules.ViewModelFactory
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.activity.list_adapter.TranslatedResultsListAdapter
import ru.androidlearning.dictionary.ui.activity.view_model.MainActivityViewModel
import ru.androidlearning.dictionary.ui.base_abstract_templates.BaseDaggerActivity
import javax.inject.Inject

class MainActivity : BaseDaggerActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        viewModelFactory.create(this).let { savedStateViewModelFactory ->
            ViewModelProvider(this, savedStateViewModelFactory)[MainActivityViewModel::class.java]
        }
    }

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val translatedResultsListAdapter: TranslatedResultsListAdapter by lazy { TranslatedResultsListAdapter() }

    override fun initViews() {
        with(viewBinding) {
            enterWordTextInputLayout.setEndIconOnClickListener {
                mainActivityViewModel.translate(
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

    override fun observeToLiveData() {
        mainActivityViewModel.dataLoadingLiveData.observe(this) { dataLoadingState ->
            renderData(dataLoadingState)
        }
    }

    override fun restoreViewState() {
        mainActivityViewModel.restoreViewState()
    }

    private fun renderData(dataLoadingState: DataLoadingState<DictionaryPresentationData>?) {
        when (dataLoadingState) {
            is DataLoadingState.Error -> doOnError(dataLoadingState.error)
            is DataLoadingState.Loading -> doOnLoading()
            is DataLoadingState.Success -> doOnSuccess(dataLoadingState.dictionaryPresentationData)
        }
    }

    private fun doOnSuccess(dictionaryPresentationData: DictionaryPresentationData) {
        hideProgressBar()
        showTranslatedResult(dictionaryPresentationData)
        if (dictionaryPresentationData.translatedWords.isNullOrEmpty()) {
            showNoDataLabel()
        } else {
            hideNoDataLabel()
        }
    }

    private fun doOnLoading() {
        showProgressBar()
    }

    private fun doOnError(e: Throwable) {
        hideProgressBar()
        showError(e.message)
    }

    private fun showTranslatedResult(dictionaryPresentationData: DictionaryPresentationData) {
        with(viewBinding) {
            translatedWordTextView.text = dictionaryPresentationData.word
            translatedResultsListAdapter.submitList(dictionaryPresentationData.translatedWords)
        }
    }

    private fun showNoDataLabel() {
        viewBinding.noDataTextView.visibility = View.VISIBLE
    }

    private fun hideNoDataLabel() {
        viewBinding.noDataTextView.visibility = View.GONE
    }

    private fun showError(errorMessage: String?) {
        Toast.makeText(this, getString(R.string.error_message_prefix) + errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar() {
        viewBinding.includedLoadingSheet.loadingSheet.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        viewBinding.includedLoadingSheet.loadingSheet.visibility = View.GONE
    }
}
