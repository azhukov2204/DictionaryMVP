package ru.androidlearning.presentation.fragments.stopwatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import ru.androidlearning.presentation.interactor.Interactor

class StopwatchViewModel(private val interactor: Interactor) : ViewModel() {

    private val stopwatchOrchestrators by lazy { interactor.getStopwatchOrchestrators() }
    val stopwatchLiveDataList: List<LiveData<String>> by lazy {
        stopwatchOrchestrators.map { stopwatchOrchestrator ->
            stopwatchOrchestrator.ticker.asLiveData()
        }
    }

    fun startStopwatch(stopwatchNumber: Int) {
        stopwatchOrchestrators
            .takeIf { it.size > stopwatchNumber }
            ?.get(stopwatchNumber)
            ?.start()
    }

    fun stopStopwatch(stopwatchNumber: Int) {
        stopwatchOrchestrators
            .takeIf { it.size > stopwatchNumber }
            ?.get(stopwatchNumber)
            ?.stop()
    }

    fun pauseStopwatch(stopwatchNumber: Int) {
        stopwatchOrchestrators
            .takeIf { it.size > stopwatchNumber }
            ?.get(stopwatchNumber)
            ?.pause()
    }

    override fun onCleared() {
        stopwatchOrchestrators.forEach { stopwatchOrchestrator ->
            stopwatchOrchestrator.stop()
        }
        super.onCleared()
    }
}
