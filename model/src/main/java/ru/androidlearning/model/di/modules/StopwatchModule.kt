package ru.androidlearning.model.di.modules

import org.koin.dsl.module
import ru.androidlearning.model.stopwatch.holder.StopwatchStateHolder
import ru.androidlearning.model.stopwatch.holder.StopwatchStateHolderImpl
import ru.androidlearning.model.stopwatch.holder.calculator.StopwatchStateCalculator
import ru.androidlearning.model.stopwatch.holder.calculator.StopwatchStateCalculatorImpl
import ru.androidlearning.model.stopwatch.holder.calculator.provider.TimestampProvider
import ru.androidlearning.model.stopwatch.holder.calculator.provider.TimestampProviderImpl

val stopwatchModule = module {
    factory<TimestampProvider> { TimestampProviderImpl() }
    factory<StopwatchStateCalculator> { StopwatchStateCalculatorImpl(timestampProvider = get()) }
    factory<StopwatchStateHolder> { StopwatchStateHolderImpl(stopwatchStateCalculator = get()) }
}
