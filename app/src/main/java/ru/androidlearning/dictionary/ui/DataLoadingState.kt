package ru.androidlearning.dictionary.ui

sealed class DataLoadingState<T> {
    data class Success<T>(val data: T) : DataLoadingState<T>()
    data class Error<T>(val error: Throwable) : DataLoadingState<T>()
    class Loading<T> : DataLoadingState<T>()
}
