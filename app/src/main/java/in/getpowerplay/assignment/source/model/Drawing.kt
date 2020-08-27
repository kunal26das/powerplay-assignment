package `in`.getpowerplay.assignment.source.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drawing(
    var id: String?,

    @SerializedName("name")
    val name: String,

    @SerializedName("time_added")
    val timeAdded: String,

    @SerializedName("url")
    val url: String
) : Parcelable