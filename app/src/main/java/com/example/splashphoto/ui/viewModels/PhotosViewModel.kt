package com.example.splashphoto.ui.viewModels

import android.util.Log
import android.util.Log.e
import androidx.lifecycle.*
import com.example.splashphoto.R
import com.example.splashphoto.common.state.UiState
import com.example.splashphoto.di.MainDispatcher
import com.example.splashphoto.domain.model.Ad
import com.example.splashphoto.domain.model.Photo
import com.example.splashphoto.domain.model.PhotoConst
import com.example.splashphoto.domain.usecase.GetPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.util.*

import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _photoListState = MutableLiveData<UiState<List<Any>>>(UiState.Init())
    val photoListState: LiveData<UiState<List<Any>>> = _photoListState

    private var nextPage = PhotoConst.startPage
    private var photoList : MutableList<Photo> = mutableListOf()


    val isLoading :Boolean
    get() = _photoListState.value is UiState.Loading || _photoListState.value is UiState.LoadMore

    var isLastPage :Boolean = false

    init {
       Log.e(  "initViewModel","initialization")
        loadPhotos(nextPage)
    }

     fun loadPhotos(page: Int = nextPage , pageSize :Int = PhotoConst.pageSize) {
         Log.e("list" , photoList.size.toString() + "size")
      getPhotoUseCase(page ,pageSize)
          .onStart {
          _photoListState.value =
              if (page == PhotoConst.startPage)
                  UiState.Loading()
              else
                  UiState.LoadMore()

      }.map {
          if(page != PhotoConst.startPage && it.isEmpty())

              isLastPage = true
              nextPage++
              photoList.addAll(it)
              photoList.toList()


          }
          .map {

              if(it.isEmpty()) UiState.Empty() else  UiState.Success(insertAdAfterEveryFivePhotos(it))
          }
          .onEach { _photoListState.value = it }
          .catch {
              Timber.tag("view model err").e(it.message.toString())
              Timber.e(it)
              if (photoList.isEmpty()) {
                  _photoListState.value = UiState.Error(it.message)
              } else {
                  _photoListState.value = UiState.SoftError(it.message)
              }

          }.flowOn(mainDispatcher)
          .launchIn(viewModelScope)
    }

    private fun insertAdAfterEveryFivePhotos(photos:List<Photo>):List<Any>{
        val list :MutableList<Any> = photos.toMutableList()
        val adsCount = photos.size /5
        var lastAdIndex = 5
        for (i in 1..adsCount){
           list.add(lastAdIndex,getFixedAd())
            lastAdIndex += 6
        }
        return list.toList()
    }

    private fun getFixedAd() = Ad(R.drawable.ic_fixed_advertisement , "")

    fun resetList(){
        nextPage = PhotoConst.startPage
        photoList.clear()
    }
}