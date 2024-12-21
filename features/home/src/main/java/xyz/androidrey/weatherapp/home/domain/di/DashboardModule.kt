package xyz.androidrey.weatherapp.home.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.androidrey.weatherapp.home.data.repository.CurrentRepository
import xyz.androidrey.weatherapp.home.domain.repository.CurrentRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DashboardModule {

    @Singleton
    @Provides
    fun provideAuthRepository(dashboardRepository: CurrentRepositoryImpl): CurrentRepository {
        return dashboardRepository
    }
}