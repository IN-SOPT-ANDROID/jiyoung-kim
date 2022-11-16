package org.sopt.sample.data.response
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    val message: String,
    val newUser: NewUser,
    val status: Int
) {
    @Serializable
    data class NewUser(
        val bio: String?,
        val email: String,
        val id: Int,
        val name: String,
        val password: String,
        val profileImage: String?
    )
}

