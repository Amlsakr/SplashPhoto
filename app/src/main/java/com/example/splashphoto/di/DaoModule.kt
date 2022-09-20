package com.example.splashphoto.di

import androidx.room.Database
import com.example.splashphoto.common.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun providePhotosDao(database: AppDataBase) = database.getPhotosDao()
}