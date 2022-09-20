package com.example.splashphoto.data.sources

import android.util.Log
import com.example.splashphoto.data.sources.mapper.toPhotoEntityList
import com.example.splashphoto.data.sources.mapper.toPhotoList
import com.example.splashphoto.domain.PhotosRepository
import com.example.splashphoto.domain.model.Photo
import com.example.splashphoto.domain.model.PhotoConst
import kotlinx.coroutines.flow.*
import timber.log.Timber


import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val localSource :PhotoLocalSource ,
    private val remoteSource: PhotoRemoteSource
) :PhotosRepository{

    override fun getPhotoList(page: Int, pageSize: Int): Flow<List<Photo>> {
     return remoteSource.getPhotoList(page,pageSize)
         .onEach {
             if (page == PhotoConst.startPage){
                 localSource.UpdatePhotoList(it.toPhotoEntityList())
             }

     }.map {
             it.toPhotoList()
         }.catch {
             t ->
             Log.e("errrepos" , t.message.toString())
             Timber.e(t)
             if(page == PhotoConst.startPage){
                 Log.e("errrepos" , "emitALL(emptyList())")
                 emitAll(localSource.getPhotoList().map {
                     it.toPhotoList()
                 })
             }else {
                 Log.e("errrepos" , "emit(emptyList())")
                 emit(emptyList())
             }
         }
    }
}