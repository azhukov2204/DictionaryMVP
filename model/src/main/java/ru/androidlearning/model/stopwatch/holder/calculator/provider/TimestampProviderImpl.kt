package ru.androidlearning.model.stopwatch.holder.calculator.provider

class TimestampProviderImpl : TimestampProvider {
    override fun getMilliseconds(): Long =
        System.currentTimeMillis()
}
