package com.example.splashphoto.di

import com.example.splashphoto.BuildConfig
import com.example.splashphoto.common.retrofit.SynchrounsAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private  val CALL_TIMEOUT = if(BuildConfig.DEBUG) 120L else 60L
    private val READ_TIMEOUT = if(BuildConfig.DEBUG) 120L else 60L
    private val WRITE_TIMEOUT = if(BuildConfig.DEBUG) 120L else 60L
    private val CONNECTION_TIMEOUT = if(BuildConfig.DEBUG) 120L else 60L


    @MainClient
    @Provides
    @Singleton
    fun provideBaseUrl() :String = BuildConfig.BASE_URL



    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        }


    @MainClient
    @Provides
    @Singleton
    fun provideOKHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .callTimeout(CALL_TIMEOUT,TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIMEOUT,TimeUnit.SECONDS)
        .build()


    @MainClient
    @Provides
    @Singleton
    fun provideGson() :Gson = GsonBuilder().create()

    @MainClient
    @Provides
    @Singleton
    fun provideGsonConverterFactory(@MainClient gson: Gson) :GsonConverterFactory = GsonConverterFactory.create(gson)


    @MainClient
    @Provides
    @Singleton
    fun provideRetrofit(
        @MainClient baseUrl:String ,
        @MainClient okHttpClient: OkHttpClient,
        @MainClient gsonConverterFactory: GsonConverterFactory
    ):Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(SynchrounsAdapterFactory())
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()


}