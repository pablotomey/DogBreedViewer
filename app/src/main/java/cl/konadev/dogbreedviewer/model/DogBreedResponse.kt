package cl.konadev.dogbreedviewer.model

import android.os.Message
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DogBreedResponse(

    @SerializedName("message")
    val message: MutableList<String>,

    @SerializedName("status")
    val status: String

): Parcelable