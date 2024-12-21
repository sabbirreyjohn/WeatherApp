package xyz.androidrey.multimoduletemplate.network.http

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultError(
    @SerialName("error")
    val error: ErrorDetails
) {
    @Serializable
    data class ErrorDetails(
        @SerialName("code")
        val code: Int,
        @SerialName("message")
        val message: String
    )
}
