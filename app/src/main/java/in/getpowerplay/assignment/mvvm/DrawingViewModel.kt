package `in`.getpowerplay.assignment.mvvm

import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.source.repository.DrawingRepository
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.mvvm.ViewModel
import androidx.essentials.events.Events
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class DrawingViewModel : ViewModel() {

    val idToken = MutableLiveData<String>()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val compositeDisposable = CompositeDisposable()
    val drawings = MutableLiveData<List<Drawing>>(null)
    private val firebaseStorage = FirebaseStorage.getInstance()
    private val drawingRepository by inject<DrawingRepository>()

    init {
        firebaseAuth.currentUser?.getIdToken(true)
            ?.addOnSuccessListener {
                idToken.value = it.token
            }
    }

    fun getDrawings() {
        compositeDisposable.add(
            drawingRepository.getDrawings(idToken.value!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach {
                        it.value.id = it.key
                        it.value.url =
                            "https://firebasestorage.googleapis.com${it.value.url}?alt=media"
                    }
                    drawings.value = it.values.toList()
                }, {
                    drawings.value = emptyList()
                })
        )
    }

    fun postDrawing(drawing: Drawing, drawingImage: ByteArray) {
        firebaseStorage.reference.child("${UUID.randomUUID()}.jpg").apply {
            putBytes(drawingImage).addOnSuccessListener {
                downloadUrl.addOnSuccessListener {
                    drawing.url = it.path!!
                    compositeDisposable.add(
                        drawingRepository.postDrawing(idToken.value!!, drawing)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                Events.publish(drawing)
                            }, {})
                    )
                }
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
