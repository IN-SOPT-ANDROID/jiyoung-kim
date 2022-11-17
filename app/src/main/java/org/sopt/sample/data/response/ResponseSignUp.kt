package org.sopt.sample.data.response
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    val status: Int,
    val message: String,
    val newUser: NewUser
) {
    @Serializable
    data class NewUser(
        val id: Int,
        val name: String,
        val profileImage: String?,
        val bio: String?,
        val email: String,
        val password: String

    )
}

