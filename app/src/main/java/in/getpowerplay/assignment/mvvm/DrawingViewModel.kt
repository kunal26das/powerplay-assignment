package `in`.getpowerplay.assignment.mvvm

import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.source.repository.DrawingRepository
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.mvvm.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DrawingViewModel : ViewModel() {

    val idToken = MutableLiveData<String>()
    private val firebaseAuth = FirebaseAuth.getInstance()
    val drawings = MutableLiveData<List<Drawing>>(null)
    private val drawingRepository by inject<DrawingRepository>()

    init {
        firebaseAuth.currentUser?.getIdToken(true)
            ?.addOnSuccessListener {
                idToken.value = it.token
            }
    }

    fun getDrawings(idToken: String) {
        drawingRepository.getDrawings(idToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                drawings.value = it
            }, {
                drawings.value = emptyList()
            })
    }

}
