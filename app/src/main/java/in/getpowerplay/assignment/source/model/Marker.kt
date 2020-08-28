package `in`.getpowerplay.assignment.source.model

import `in`.getpowerplay.assignment.utils.Calendar.currentDate
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Marker(
    var id: String? = null,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("description")
    var description: String = "",

    @SerializedName("time_created")
    val timeCreated: String = currentDate,

    @SerializedName("abscissa")
    val x: Float,

    @SerializedName("ordinate")
    val y: Float
) : Parcelable