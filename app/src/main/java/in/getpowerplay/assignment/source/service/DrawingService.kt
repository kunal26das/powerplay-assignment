package `in`.getpowerplay.assignment.source.service

import `in`.getpowerplay.assignment.source.model.Drawing
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DrawingService {

    @GET("/drawings.json")
    fun getDrawings(
        @Query("auth") idToken: String
    ): Single<Map<String, Drawing>>

    @POST("/drawings.json")
    fun postDrawing(
        @Query("auth") idToken: String,
        @Body drawing: Drawing
    ): Single<Any>

}