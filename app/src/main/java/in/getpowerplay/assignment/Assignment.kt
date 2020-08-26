package `in`.getpowerplay.assignment

import `in`.getpowerplay.assignment.ui.main.MainViewModel
import `in`.getpowerplay.assignment.ui.splash.SplashViewModel
import androidx.essentials.firebase.FirebaseApplication
import com.facebook.stetho.Stetho

class Assignment : FirebaseApplication() {

    override val sharedPreferences get() = getSharedPreferences(packageName, MODE_PRIVATE)!!

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initViewModels()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initViewModels() {
        viewModel { SplashViewModel() }
        viewModel { MainViewModel() }
    }

}