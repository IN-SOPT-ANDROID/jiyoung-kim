package org.sopt.sample.data

import androidx.annotation.DrawableRes

data class PlaylistData (
    @DrawableRes val image:Int,
    val name: String,
    val singer: String,
)