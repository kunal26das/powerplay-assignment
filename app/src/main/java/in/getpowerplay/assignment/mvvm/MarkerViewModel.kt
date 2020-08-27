package `in`.getpowerplay.assignment.mvvm

import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.source.model.Marker
import `in`.getpowerplay.assignment.source.repository.MarkerRepository
import androidx.essentials.core.injector.KoinComponent.inject
import androidx.essentials.core.mvvm.ViewModel
import androidx.essentials.events.Events
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MarkerViewModel : ViewModel() {

    val idToken = MutableLiveData<String>()
    private val firebaseAuth = FirebaseAuth.getInstance()
    val markers = MutableLiveData<List<Marker>>(null)
    private val compositeDisposable = CompositeDisposable()
    private val markerRepository by inject<MarkerRepository>()

    init {
        firebaseAuth.currentUser?.getIdToken(true)
            ?.addOnSuccessListener {
                idToken.value = it.token
            }
    }

    fun getMarkers(drawing: Drawing) {
        compositeDisposable.add(
            markerRepository.getMarkers(idToken.value!!, drawing)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach {
                        it.value.id = it.key
                    }
                    markers.value = it.values.toList()
                }, {
                    markers.value = emptyList()
                })
        )
    }

    fun postMarkers(drawing: Drawing, marker: Marker) {
        compositeDisposable.add(
            markerRepository.postMarker(idToken.value!!, drawing, marker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Events.publish(marker)
                }, {})
        )
    }

    fun deleteMarker(drawing: Drawing, marker: Marker) {
        compositeDisposable.add(
            markerRepository.deletemarker(idToken.value!!, drawing, marker)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Events.publish(marker)
                }, {})
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}