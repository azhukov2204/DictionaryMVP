package ru.androidlearning.model.stopwatch

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.androidlearning.model.stopwatch.holder.StopwatchStateHolder

class StopwatchOrchestratorImpl(private val stopwatchStateHolder: StopwatchStateHolder) : StopwatchOrchestrator {
    companion object {
        private const val DEFAULT_DELAY_MS = 20L
        private const val STOPWATCH_INITIAL_VALUE = "00:00:000"
    }

    private val mutableTicker = MutableStateFlow(STOPWATCH_INITIAL_VALUE)
    override val ticker: StateFlow<String> = mutableTicker

    private val scope by lazy {
        CoroutineScope(
            Dispatchers.Default
                    + SupervisorJob()
                    + CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }
        )
    }

    override fun start() {
        startJob()
        stopwatchStateHolder.start()
    }

    override fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    override fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun startJob() {
        scope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(DEFAULT_DELAY_MS)
            }
        }
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
    }

    private fun clearValue() {
        mutableTicker.value = STOPWATCH_INITIAL_VALUE
    }
}
