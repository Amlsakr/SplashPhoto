package com.example.splashphoto.data.sources

import com.example.splashphoto.data.sources.local.model.PhotoEntity
import com.example.splashphoto.data.sources.remote.model.PhotoRemote
import kotlinx.coroutines.flow.Flow


interface PhotoLocalSource {

    fun getPhotoList():Flow<List<PhotoEntity>>
    suspend fun UpdatePhotoList(list: List<PhotoEntity>)
}

interface PhotoRemoteSource {

    fun getPhotoList(page:Int , pageSize:Int) : Flow<List<PhotoRemote>>
}
