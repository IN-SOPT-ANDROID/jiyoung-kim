package org.sopt.sample.data.response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable

data class ResponseSignUp (
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("newUser")
    val newUser: NewUser,
) {
    @Serializable
    data class NewUser(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("profileImage")
        val profileImage: String?,
        @SerialName("bio")
        val bio: String?,
        @SerialName("email")
        val email: String,
        @SerialName("password")
        val password: String
    )
}