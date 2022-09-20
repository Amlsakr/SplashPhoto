package com.example

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.splashphoto.App
import com.example.splashphoto.common.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDataBaseModule {

    @Provides
    @Singleton
    fun providesAppDataBase(@ApplicationContext appContext: Context) :AppDataBase =
        Room.databaseBuilder(
            appContext.applicationContext,
            AppDataBase::class.java,
            AppDataBase.DATA_BASE_NAME
        ).fallbackToDestructiveMigration()
            .build()


}