package `in`.getpowerplay.assignment.source.service

import `in`.getpowerplay.assignment.source.model.Drawing
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DrawingService {

    @GET("/drawings.json")
    fun getDrawings(@Query("auth") idToken: String): Single<List<Drawing>>

}