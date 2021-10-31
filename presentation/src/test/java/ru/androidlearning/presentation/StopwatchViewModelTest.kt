package ru.androidlearning.presentation

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.androidlearning.model.stopwatch.StopwatchOrchestrator
import ru.androidlearning.presentation.fragments.stopwatch.StopwatchViewModel
import ru.androidlearning.presentation.interactor.Interactor

class StopwatchViewModelTest {
    companion object {
        const val STOPWATCH_COUNT = 2
    }

    @Mock
    private lateinit var interactor: Interactor

    private val stopwatchViewModel by lazy { StopwatchViewModel(interactor) }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(interactor.getStopwatchOrchestrators()).thenReturn(
            Array(STOPWATCH_COUNT) { mock(StopwatchOrchestrator::class.java) }
        )
    }

    @Test
    fun startStopwatch_test() {
        for (i in 0 until STOPWATCH_COUNT) {
            stopwatchViewModel.startStopwatch(i)
            verify(interactor.getStopwatchOrchestrators()[i], times(1)).start()
        }
    }

    @Test
    fun stopStopwatch_test() {
        for (i in 0 until STOPWATCH_COUNT) {
            stopwatchViewModel.stopStopwatch(i)
            verify(interactor.getStopwatchOrchestrators()[i], times(1)).stop()
        }
    }

    @Test
    fun pauseStopwatch_test() {
        for (i in 0 until STOPWATCH_COUNT) {
            stopwatchViewModel.pauseStopwatch(i)
            verify(interactor.getStopwatchOrchestrators()[i], times(1)).pause()
        }
    }
}
