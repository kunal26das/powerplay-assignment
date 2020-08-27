package `in`.getpowerplay.assignment

import `in`.getpowerplay.assignment.mvvm.DrawingViewModel
import `in`.getpowerplay.assignment.mvvm.MarkerViewModel
import `in`.getpowerplay.assignment.mvvm.SplashViewModel
import `in`.getpowerplay.assignment.source.repository.DrawingRepository
import `in`.getpowerplay.assignment.source.repository.MarkerRepository
import androidx.essentials.events.Events
import androidx.essentials.firebase.FirebaseApplication
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Assignment : FirebaseApplication() {

    override val sharedPreferences get() = getSharedPreferences(packageName, MODE_PRIVATE)!!

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initRetrofit()
        initViewModels()
        initRepositories()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initRetrofit() {
        single {
            Retrofit.Builder().apply {
                client(OkHttpClient.Builder().apply {
                    if (BuildConfig.DEBUG) {
                        addNetworkInterceptor(StethoInterceptor())
                    }
                    retryOnConnectionFailure(true)
                }.build())
                baseUrl(BuildConfig.FIREBASE_DATABASE_URL)
                addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            }.build()
        }
    }

    private fun initViewModels() {
        viewModel { SplashViewModel() }
        viewModel { DrawingViewModel() }
        viewModel { MarkerViewModel() }
    }

    private fun initRepositories() {
        single { DrawingRepository() }
        single { MarkerRepository() }
    }

    override fun onTerminate() {
        Events.clear()
        super.onTerminate()
    }

}