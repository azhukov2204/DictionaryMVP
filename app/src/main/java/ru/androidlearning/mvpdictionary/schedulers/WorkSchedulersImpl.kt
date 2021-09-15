package ru.androidlearning.mvpdictionary.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class WorkSchedulersImpl : WorkSchedulers {
    override fun threadMain(): Scheduler = AndroidSchedulers.mainThread()
    override fun threadIO(): Scheduler = Schedulers.io()
}
