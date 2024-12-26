package live.studyquran.android.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import live.studyquran.android.common.BuildConfig
import live.studyquran.android.common.domain.repository.DataStoreRepository
import live.studyquran.android.common.data.repository.DataStoreRepositoryImpl
import xyz.androidrey.multimoduletemplate.network.http.HttpClientBuilder
import xyz.androidrey.multimoduletemplate.network.http.RequestHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient =
        HttpClientBuilder().protocol(URLProtocol.HTTPS).host(BuildConfig.base_url).build()

    @Provides
    @Singleton
    fun provideRequestHandler(client: HttpClient) = RequestHandler(client)

    @Provides
    @Singleton
    fun providesDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }
}