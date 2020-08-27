package `in`.getpowerplay.assignment.source.service

import `in`.getpowerplay.assignment.source.model.Marker
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface MarkerService {

    @GET("/drawings/{drawing_id}/markers.json")
    fun getMarkers(
        @Path("drawing_id") drawingId: String,
        @Query("auth") idToken: String
    ): Single<Map<String, Marker>>

    @POST("/drawings/{drawing_id}/markers.json")
    fun postMarker(
        @Path("drawing_id") drawingId: String,
        @Query("auth") idToken: String,
        @Body marker: Marker
    ): Single<Any>

    @DELETE("/drawings/{drawing_id}/markers/{marker_id}.json")
    fun deleteMarker(
        @Path("drawing_id") drawingId: String,
        @Path("marker_id") markerId: String,
        @Query("auth") idToken: String
    ): Single<Any>

}