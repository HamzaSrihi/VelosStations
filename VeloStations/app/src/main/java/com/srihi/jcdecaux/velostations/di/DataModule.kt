package com.srihi.jcdecaux.velostations.di

import com.srihi.jcdecaux.data.repository.VeloStationRepository
import com.srihi.jcdecaux.domain.usecases.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(stationRepository: VeloStationRepository): DataRepositorySource
}
