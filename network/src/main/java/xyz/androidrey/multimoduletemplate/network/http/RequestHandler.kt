package xyz.androidrey.multimoduletemplate.network.http

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import xyz.androidrey.multimoduletemplate.network.NetworkException
import xyz.androidrey.multimoduletemplate.network.NetworkResult

class RequestHandler(val httpClient: HttpClient) {

    val bearerToken = MutableStateFlow<String?>(null)

    fun setBearerToken(token: String?) {
        bearerToken.value = token
    }

    suspend inline fun <reified B, reified R> executeRequest(
        method: HttpMethod,
        urlPathSegments: List<Any>,
        body: B? = null,
        queryParams: Map<String, Any>? = null,
    ): NetworkResult<R> {
        delay(1000L)
        return withContext(Dispatchers.IO) {
            try {
                val response = httpClient.prepareRequest {
                    this.method = method
                    url {
                        val pathSegments = urlPathSegments.map { it.toString() }
                        appendPathSegments(pathSegments)
                    }
                    body?.let { setBody(body) }
                    queryParams?.let { params ->
                        params.forEach { (key, value) ->
                            parameter(key, value)
                        }
                    }
                    // Add the Bearer Token dynamically
                    bearerToken.value?.let { token ->
                        header(HttpHeaders.Authorization, "Bearer $token")
                    }
                }.execute().body<R>()
                NetworkResult.Success(response)
            } catch (e: Exception) {
                val networkException = if (e is ResponseException) {
                    val errorBody = e.response.body<DefaultError>()
                    when (e.response.status) {
                        HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(
                            errorBody.error.message,
                            e,
                        )

                        HttpStatusCode.BadRequest, HttpStatusCode.Forbidden -> NetworkException.NotFoundException(
                            errorBody.error.message,
                            e
                        )

                        else -> NetworkException.NotFoundException("API Not found", e)
                    }
                } else {
                    NetworkException.UnknownException(e.message ?: "Network Error, Please try again", e)
                }
                NetworkResult.Error(null, networkException)
            }
        }
    }

    suspend inline fun <reified R> get(
        urlPathSegments: List<Any>,
        queryParams: Map<String, Any>? = null,
    ): NetworkResult<R> = executeRequest<Any, R>(
        method = HttpMethod.Get,
        urlPathSegments = urlPathSegments.toList(),
        queryParams = queryParams,
    )

    suspend inline fun <reified B, reified R> post(
        urlPathSegments: List<Any>,
        body: B? = null,
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Post,
        urlPathSegments = urlPathSegments.toList(),
        body = body,
    )

    suspend inline fun <reified B, reified R> patch(
        urlPathSegments: List<Any>,
        body: B? = null,
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Patch,
        urlPathSegments = urlPathSegments.toList(),
        body = body,
    )

    suspend inline fun <reified B, reified R> put(
        urlPathSegments: List<Any>,
        body: B? = null,
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Put,
        urlPathSegments = urlPathSegments.toList(),
        body = body,
    )

    suspend inline fun <reified B, reified R> delete(
        urlPathSegments: List<Any>,
        body: B? = null,
    ): NetworkResult<R> = executeRequest(
        method = HttpMethod.Delete,
        urlPathSegments = urlPathSegments.toList(),
        body = body,
    )
}
