package `in`.getpowerplay.assignment.source.repository

import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.source.model.Marker
import `in`.getpowerplay.assignment.source.service.MarkerService
import androidx.essentials.core.injector.KoinComponent.inject
import retrofit2.Retrofit

class MarkerRepository {

    private val retrofit by inject<Retrofit>()
    private val service = retrofit.create(MarkerService::class.java)

    fun getMarkers(idToken: String, drawing: Drawing) =
        service.getMarkers(drawing.id!!, idToken)

    fun postMarker(idToken: String, drawing: Drawing, marker: Marker) =
        service.postMarker(drawing.id!!, idToken, marker)

    fun deletemarker(idToken: String, drawing: Drawing, marker: Marker) =
        service.deleteMarker(drawing.id!!, marker.id!!, idToken)

}