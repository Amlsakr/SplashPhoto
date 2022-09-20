package com.example.splashphoto.common.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class SynchrounsAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array< Annotation?>?,
        retrofit: Retrofit?
    ): CallAdapter<Any, Any>? {
       return if(returnType.toString().contains("retrofit2.Call")){
           null
       }else object :CallAdapter<Any,Any>{
           override fun responseType(): Type {
             return returnType
           }

           override fun adapt(call: Call<Any?>): Any? {
               return try {
                   call.execute().body()
               }catch (e:Exception){
                   throw RuntimeException(e)
               }
           }

       }
    }

    companion object{
        fun create() : CallAdapter.Factory{
            return SynchrounsAdapterFactory.create()
        }
    }
}

