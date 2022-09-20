package com.example.splashphoto.di

import com.example.splashphoto.data.sources.PhotoLocalSource
import com.example.splashphoto.data.sources.PhotoRemoteSource
import com.example.splashphoto.data.sources.local.PhotoLocalSourceImpl
import com.example.splashphoto.data.sources.remote.PhotoRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindPhotosLocalSource (photosLocalSource :PhotoLocalSourceImpl):PhotoLocalSource

    @Binds
    abstract fun bindPhotosRemoteSource (photoRemoteSource: PhotoRemoteSourceImpl) :PhotoRemoteSource
}