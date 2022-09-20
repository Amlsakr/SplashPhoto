package com.example.splashphoto.domain

import com.example.splashphoto.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getPhotoList(page:Int , pageSize:Int) : Flow<List<Photo>>
}