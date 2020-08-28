package `in`.getpowerplay.assignment.source.model

import `in`.getpowerplay.assignment.utils.Calendar.currentDate
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drawing(
    var id: String? = null,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("time_added")
    val timeAdded: String = currentDate,

    @SerializedName("url")
    var url: String = ""
) : Parcelable