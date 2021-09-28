package ru.androidlearning.dictionary.ui.base_abstract_templates

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseMVVMFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeToLiveData()
        restoreViewState()
    }

    protected abstract fun initViews()
    protected abstract fun observeToLiveData()
    protected abstract fun restoreViewState()
}
