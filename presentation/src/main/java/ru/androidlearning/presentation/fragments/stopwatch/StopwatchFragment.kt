package ru.androidlearning.presentation.fragments.stopwatch

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.androidlearning.core.base_abstract_templates.BaseMVVMFragment
import ru.androidlearning.fragments.R
import ru.androidlearning.fragments.databinding.FragmentStopwatchBinding

class StopwatchFragment : BaseMVVMFragment(R.layout.fragment_stopwatch) {

    private val viewBinding: FragmentStopwatchBinding by viewBinding(FragmentStopwatchBinding::bind)
    private val router: Router by inject()
    private val stopwatchViewModel: StopwatchViewModel by viewModel()

    override fun initViews() {
        initToolbar()
        initButtons()
    }

    override fun observeToLiveData() {
        with(stopwatchViewModel) {
            stopwatchLiveDataList
                .takeIf { it.isNotEmpty() }
                ?.first()
                ?.observe(viewLifecycleOwner) { time ->
                    viewBinding.textTime.text = time
                }

            stopwatchLiveDataList
                .takeIf { it.size > 1 }
                ?.get(1)
                ?.observe(viewLifecycleOwner) { time ->
                    viewBinding.textTimeSecond.text = time
                }
        }
    }

    private fun initButtons() {
        with(viewBinding) {
            if (stopwatchViewModel.stopwatchLiveDataList.isNotEmpty()) {
                buttonStart.setOnClickListener { stopwatchViewModel.startStopwatch(stopwatchNumber = 0) }
                buttonPause.setOnClickListener { stopwatchViewModel.pauseStopwatch(stopwatchNumber = 0) }
                buttonStop.setOnClickListener { stopwatchViewModel.stopStopwatch(stopwatchNumber = 0) }
            }

            if (stopwatchViewModel.stopwatchLiveDataList.size > 1) {
                buttonStartSecond.setOnClickListener { stopwatchViewModel.startStopwatch(stopwatchNumber = 1) }
                buttonPauseSecond.setOnClickListener { stopwatchViewModel.pauseStopwatch(stopwatchNumber = 1) }
                buttonStopSecond.setOnClickListener { stopwatchViewModel.stopStopwatch(stopwatchNumber = 1) }
            }
        }
    }

    override fun restoreViewState() {}

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
        fun newInstance() = StopwatchFragment()
    }
}
