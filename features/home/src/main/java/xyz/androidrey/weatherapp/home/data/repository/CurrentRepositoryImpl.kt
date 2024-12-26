package xyz.androidrey.weatherapp.home.data.repository

import live.studyquran.android.common.BuildConfig
import xyz.androidrey.multimoduletemplate.network.http.RequestHandler
import xyz.androidrey.weatherapp.home.domain.entity.CurrentData
import xyz.androidrey.weatherapp.home.domain.repository.CurrentRepository
import javax.inject.Inject

open class CurrentRepositoryImpl @Inject constructor(private val requestHandler: RequestHandler) :
    CurrentRepository {
    override suspend fun getCurrentData(query: String) =
        requestHandler.get<CurrentData>(
            urlPathSegments = listOf("v1", "current.json"),
            queryParams = mapOf("q" to query, "key" to BuildConfig.api_key)
        )
}