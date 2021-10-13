package ru.androidlearning.core.base_abstract_templates

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

abstract class BaseMVVMFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()

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
