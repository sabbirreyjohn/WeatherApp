package xyz.androidrey.weatherapp.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.androidrey.weatherapp.home.domain.repository.CurrentRepository
import xyz.androidrey.weatherapp.home.data.repository.CurrentRepositoryImpl
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