package ru.androidlearning.dictionary.ui.fragments.history

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.androidlearning.dictionary.R
import ru.androidlearning.dictionary.databinding.FragmentHistoryBinding
import ru.androidlearning.dictionary.navigation.DetailsFragmentScreen
import ru.androidlearning.dictionary.ui.DataLoadingState
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.base_abstract_templates.BaseMVVMFragment

class HistoryFragment : BaseMVVMFragment(R.layout.fragment_history) {

    private val viewBinding: FragmentHistoryBinding by viewBinding(FragmentHistoryBinding::bind)
    private val router: Router by inject()
    private val historyFragmentViewModel: HistoryFragmentViewModel by viewModel()
    private val historyListAdapter by lazy { HistoryListAdapter(::onItemClick) }

    override fun initViews() {
        initToolbar()
        viewBinding.historyRecyclerView.apply {
            adapter = historyListAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    override fun observeToLiveData() {
        with(historyFragmentViewModel) {
            getLiveData().observe(this@HistoryFragment) { dataLoadingState -> renderData(dataLoadingState) }
            getHistory()
        }
    }

    private fun renderData(dataLoadingState: DataLoadingState<DictionaryPresentationData>) {
        when (dataLoadingState) {
            is DataLoadingState.Error -> doOnGetHistoryError(dataLoadingState.error)
            is DataLoadingState.Loading -> doOnHistoryLoading()
            is DataLoadingState.Success -> doOnGetHistorySuccess(dataLoadingState.data)
        }
    }

    private fun doOnGetHistorySuccess(historyData: DictionaryPresentationData) {
        showProgressBar(false)
        historyListAdapter.submitList(historyData.translatedWords)
        if (historyData.translatedWords.isNullOrEmpty()) {
            showNoDataLabel(true)
        } else {
            showNoDataLabel(false)
        }
    }

    private fun doOnHistoryLoading() {
        showProgressBar(true)
    }

    private fun doOnGetHistoryError(e: Throwable) {
        showProgressBar(false)
        showError(e.message)
    }

    private fun onItemClick(dictionaryPresentationData: DictionaryPresentationData.TranslatedWord) {
        router.navigateTo(DetailsFragmentScreen(dictionaryPresentationData))
    }

    private fun showProgressBar(isShow: Boolean) {
        viewBinding.includedLoadingSheet.loadingSheet.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showError(errorMessage: String?) {
        Toast.makeText(requireContext(), getString(R.string.error_message_prefix) + errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showNoDataLabel(isShow: Boolean) {
        viewBinding.noDataTextView.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun restoreViewState() {
    }

    private fun initToolbar() {
        (context as AppCompatActivity).apply {
            setSupportActionBar(viewBinding.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setDisplayShowTitleEnabled(true)
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            router.exit()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}
