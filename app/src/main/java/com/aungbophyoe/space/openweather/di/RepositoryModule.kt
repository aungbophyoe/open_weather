package com.aungbophyoe.space.openweather.di

import com.aungbophyoe.space.openweather.mapper.CurrentWeatherNetworkMapper
import com.aungbophyoe.space.openweather.mapper.NetworkMapper
import com.aungbophyoe.space.openweather.network.ApiService
import com.aungbophyoe.space.openweather.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(apiService: ApiService, networkMapper: NetworkMapper,currentWeatherNetworkMapper: CurrentWeatherNetworkMapper):MainRepository{
        return MainRepository(apiService = apiService,networkMapper = networkMapper,currentWeatherNetworkMapper = currentWeatherNetworkMapper)
    }
}