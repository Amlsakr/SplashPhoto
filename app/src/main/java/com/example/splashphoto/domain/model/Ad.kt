package com.example.splashphoto.domain.model

import androidx.annotation.DrawableRes

data class Ad(
    @DrawableRes val imageRes:Int ,
    val url : String
)
