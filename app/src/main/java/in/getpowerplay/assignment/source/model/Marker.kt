package `in`.getpowerplay.assignment.source.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Marker(
    var id: String?,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("time_created")
    val timeCreated: String,

    @SerializedName("abscissa")
    val x: Float,

    @SerializedName("ordinate")
    val y: Float
) : Parcelable