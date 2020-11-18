package `in`.getpowerplay.assignment.mvvm

import androidx.essentials.core.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = MutableLiveData(firebaseAuth.currentUser)

    init {
        firebaseAuth.signInAnonymously().addOnSuccessListener {
            currentUser.value = it.user
        }
    }

}