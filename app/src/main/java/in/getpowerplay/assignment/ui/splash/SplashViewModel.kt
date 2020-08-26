package `in`.getpowerplay.assignment.ui.splash

import androidx.essentials.core.mvvm.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = MutableLiveData(firebaseAuth.currentUser)

    init {
        firebaseAuth.signInAnonymously()
    }

}