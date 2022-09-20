package com.example.splashphoto.data.sources.local

import androidx.room.Dao
import com.example.splashphoto.data.sources.PhotoLocalSource
import com.example.splashphoto.data.sources.local.dao.PhotosDao
import com.example.splashphoto.data.sources.local.model.PhotoEntity
import com.example.splashphoto.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PhotoLocalSourceImpl @Inject constructor(
private val photoDao: PhotosDao,
@IoDispatcher private val ioDispatcher: CoroutineDispatcher
)
    : PhotoLocalSource {
    override fun getPhotoList(): Flow<List<PhotoEntity>> = flow {
        emitAll(photoDao.getPhotosList())
    }.flowOn(ioDispatcher)

    override suspend fun UpdatePhotoList(list: List<PhotoEntity>) {
       photoDao.updatePhotos(list)
    }
}