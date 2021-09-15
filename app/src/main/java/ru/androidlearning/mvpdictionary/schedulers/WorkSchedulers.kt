package ru.androidlearning.mvpdictionary.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface WorkSchedulers {
    fun threadIO(): Scheduler
    fun threadMain(): Scheduler
}
