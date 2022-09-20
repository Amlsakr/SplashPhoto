package com.example.splashphoto.di

import com.example.splashphoto.data.sources.remote.PhotosApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun providePhotoApiService(@MainClient retrofit :Retrofit):PhotosApiService{
        return  retrofit.create(PhotosApiService::class.java)

    }
}