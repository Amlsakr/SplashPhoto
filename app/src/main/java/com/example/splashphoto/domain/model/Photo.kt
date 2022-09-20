package com.example.splashphoto.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id:String,
    val author :String ,
    val photoUrl :String
):Parcelable
