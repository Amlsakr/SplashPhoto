package com.example.splashphoto.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.splashphoto.data.sources.local.dao.PhotosDao
import com.example.splashphoto.data.sources.local.model.PhotoEntity

@Database(
    version = 1,
    exportSchema = false ,
    entities = [PhotoEntity::class]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getPhotosDao () : PhotosDao

    companion object {
        const val DATA_BASE_NAME = "splash_database"
    }
}