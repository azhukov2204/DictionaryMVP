package ru.androidlearning.dictionary.schedulers

object WorkSchedulersFactory {
    fun create(): WorkSchedulers = WorkSchedulersImpl()
}
