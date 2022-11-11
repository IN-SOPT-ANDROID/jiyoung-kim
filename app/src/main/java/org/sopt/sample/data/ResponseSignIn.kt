package org.sopt.sample.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Objects

@Serializable

data class ResponseSignIn(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
    @SerialName("bio")
    val bio: String?,
    @SerialName("email")
    val email: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String,
    @SerialName("profileImage")
    val profileImage: String?
    )
}
