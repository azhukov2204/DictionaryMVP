package ru.androidlearning.mvpdictionary.schedulers

object WorkSchedulersFactory {
    fun create(): WorkSchedulers = WorkSchedulersImpl()
}
