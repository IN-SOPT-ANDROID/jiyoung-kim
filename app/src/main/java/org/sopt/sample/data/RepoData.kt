package org.sopt.sample.data

import androidx.annotation.DrawableRes

data class RepoData (
    @DrawableRes val image:Int,
    val name: String,
    val author: String,
)