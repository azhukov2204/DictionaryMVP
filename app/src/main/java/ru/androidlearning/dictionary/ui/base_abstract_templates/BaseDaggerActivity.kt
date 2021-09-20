package ru.androidlearning.dictionary.ui.base_abstract_templates

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseDaggerActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        initViews()
        observeToLiveData()
        restoreViewState()
    }

    protected abstract fun initViews()
    protected abstract fun observeToLiveData()
    protected abstract fun restoreViewState()
}
