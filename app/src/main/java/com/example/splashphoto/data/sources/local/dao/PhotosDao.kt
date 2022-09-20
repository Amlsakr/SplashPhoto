package com.example.splashphoto.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.splashphoto.data.sources.local.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
abstract suspend fun updatePhotos(list: List<PhotoEntity>)

@Query("SELECT * from photos")
abstract  fun getPhotosList() : Flow<List<PhotoEntity>>
}