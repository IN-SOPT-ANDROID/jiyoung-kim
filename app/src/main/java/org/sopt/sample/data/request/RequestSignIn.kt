package org.sopt.sample.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


// Request와 Response Body를 관리하는 Model Class

// Q: DTO를 RequestSignIn / ResponseSignIn 하나의 파일에서 써도 되는지?

@Serializable
data class RequestSignIn(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)
