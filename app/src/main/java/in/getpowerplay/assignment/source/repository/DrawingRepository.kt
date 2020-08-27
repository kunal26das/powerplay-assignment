package `in`.getpowerplay.assignment.source.repository

import `in`.getpowerplay.assignment.source.model.Drawing
import `in`.getpowerplay.assignment.source.service.DrawingService
import androidx.essentials.core.injector.KoinComponent.inject
import retrofit2.Retrofit

class DrawingRepository {

    private val retrofit by inject<Retrofit>()
    private val service = retrofit.create(DrawingService::class.java)
    fun getDrawings(idToken: String) = service.getDrawings(idToken)
    fun postDrawing(idToken: String, drawing: Drawing) = service.postDrawing(idToken, drawing)

}