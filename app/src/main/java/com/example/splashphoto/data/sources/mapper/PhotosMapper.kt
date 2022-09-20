package com.example.splashphoto.data.sources.mapper

import com.example.splashphoto.data.sources.local.model.PhotoEntity
import com.example.splashphoto.data.sources.remote.model.PhotoRemote
import com.example.splashphoto.domain.model.Photo

fun PhotoEntity.toPhoto() :Photo = Photo(
    id = id ,
    author = author ,
    photoUrl = photoUrl
)


@JvmName("mapEntityListToPhotoList")
fun List<PhotoEntity>.toPhotoList() = map {
    it.toPhoto()
}

fun PhotoRemote.toPhoto () = Photo(
    id = id.orEmpty(),
    author = author.orEmpty(),
    photoUrl = downloadUrl.orEmpty()

)
@JvmName("mapRemoteListToPhotoList")
fun List<PhotoRemote>.toPhotoList() = map {it.toPhoto()
}


fun PhotoRemote.toPhotoEntity() = PhotoEntity(
    id = id.orEmpty() ,
    author = author.orEmpty(),
   photoUrl  = url.orEmpty() ,


)
fun List<PhotoRemote>.toPhotoEntityList() = map {
    it.toPhotoEntity()
}