package ru.androidlearning.dictionary.ui.activity

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope
import ru.androidlearning.dictionary.R
import ru.androidlearning.presentation.navigation.SearchFragmentScreen

class MainActivity : AppCompatActivity(R.layout.activity_main), AndroidScopeComponent {

    companion object {
        private const val EXIT_SPLASH_SCREEN_ANIMATION_DURATION_MS = 1000L
        private const val SPLASH_SCREEN_ANIMATION_DURATION_MS = 1000L
    }

    override val scope: Scope by activityScope()
    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator = AppNavigator(this, R.id.main_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSplashScreen()
        savedInstanceState ?: router.newRootScreen(SearchFragmentScreen())
    }

    private fun setSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setSplashScreenHideAnimation()
        }
        setSplashScreenDuration()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setSplashScreenHideAnimation() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            ObjectAnimator.ofFloat(
                splashScreenView,
                View.ALPHA,
                1f,
                0f
            ).apply {
                duration = EXIT_SPLASH_SCREEN_ANIMATION_DURATION_MS
                doOnEnd { splashScreenView.remove() }
            }.start()
        }
    }

    private fun setSplashScreenDuration() {
        var isHideSplashScreen = false

        object : CountDownTimer(SPLASH_SCREEN_ANIMATION_DURATION_MS, SPLASH_SCREEN_ANIMATION_DURATION_MS) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean =
                    if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
            }
        )
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
