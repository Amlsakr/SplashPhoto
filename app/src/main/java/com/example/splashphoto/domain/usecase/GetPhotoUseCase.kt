package com.example.splashphoto.domain.usecase

import com.example.splashphoto.domain.PhotosRepository
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(private val photoRepository : PhotosRepository) {

    operator fun invoke (page:Int , pageSize :Int) = photoRepository.getPhotoList(page , pageSize)
}