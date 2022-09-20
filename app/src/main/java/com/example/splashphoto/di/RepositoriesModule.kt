package com.example.splashphoto.di

import com.example.splashphoto.data.sources.PhotoRepositoryImpl
import com.example.splashphoto.domain.PhotosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindPhotoRepository(photosRepository: PhotoRepositoryImpl) :PhotosRepository

}