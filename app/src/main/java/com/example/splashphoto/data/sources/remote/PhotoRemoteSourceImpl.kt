package com.example.splashphoto.data.sources.remote

import com.example.splashphoto.data.sources.PhotoRemoteSource
import com.example.splashphoto.data.sources.remote.model.PhotoRemote
import com.example.splashphoto.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PhotoRemoteSourceImpl @Inject constructor(
    private val photosApiService: PhotosApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): PhotoRemoteSource {
    override fun getPhotoList(page: Int, pageSize: Int): Flow<List<PhotoRemote>> =
        flow {
        emit(photosApiService.getPhotoList(page,pageSize))
    }.flowOn(ioDispatcher)
}