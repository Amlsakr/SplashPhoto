package com.example.splashphoto.data.sources.remote.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName

import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


@Keep
@Parcelize
 data class PhotoRemote(
    @SerializedName("author")
    var author: String? = null, // Alejandro Escamilla
    @SerializedName("download_url")
    var downloadUrl: String? = null, // https://picsum.photos/id/0/5616/3744
    @SerializedName("height")
    var height: Int? = null, // 3744
    @SerializedName("id")
    var id: String? = null, // 0
    @SerializedName("url")
    var url: String? = null, // https://unsplash.com/photos/yC-Yzbqy7PY
    @SerializedName("width")
    var width: Int? = null // 5616
):Parcelable