package org.sopt.sample.data.response

import kotlinx.serialization.Serializable

@Serializable

data class ResponseSignIn(
    val status: Int,
    val message: String,
    val result: Result
) {
    @Serializable
    data class Result(
        val id: Int,
        val name: String,
        val profileImage: String?,
        val bio: String?,
        val email: String,
        val password: String
    )
}
