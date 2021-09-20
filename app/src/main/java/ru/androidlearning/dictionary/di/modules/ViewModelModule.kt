package ru.androidlearning.dictionary.di.modules

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap
import ru.androidlearning.dictionary.ui.activity.view_model.MainActivityViewModel
import javax.inject.Inject
import kotlin.reflect.KClass

@AssistedModule
@Module(includes = [AssistedInject_ViewModelModule::class])
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindVieModelFactory(factory: MainActivityViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Reusable
class ViewModelFactory @Inject constructor(
    private val assistedFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>,
) {
    fun create(owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null): AbstractSavedStateViewModelFactory {
        return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
                assistedFactories[modelClass]?.let {
                    try {
                        return it.create(handle) as T
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                } ?: throw IllegalArgumentException("Unknown model class $modelClass")
            }
        }
    }
}

interface AssistedSavedStateViewModelFactory<T : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): T
}
