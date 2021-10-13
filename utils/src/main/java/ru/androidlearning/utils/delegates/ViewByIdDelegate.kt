package ru.androidlearning.utils.delegates

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<T : View>(
    private val rootGetter: () -> View?,
    private val viewId: Int
) {

    companion object {
        const val NO_ROOT_MESSAGE = "Cannot get View, there is no root yet"
        const val NO_VIEW_MESSAGE = "Not found View with id: "
    }

    private var rootReference: WeakReference<View>? = null
    private var viewReference: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewReference
        val cachedRoot = rootReference?.get()
        val currentRoot = rootGetter()

        if (currentRoot != cachedRoot || view == null) {
            currentRoot ?: view?.let { return it }
            ?: throw IllegalStateException(NO_ROOT_MESSAGE)

            view = currentRoot?.findViewById(viewId)
            viewReference = view
            rootReference = WeakReference(currentRoot)
        }

        checkNotNull(view) { NO_VIEW_MESSAGE + "\"$viewId\"" }
        return view
    }
}

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> =
    ViewByIdDelegate({ view }, viewId)
