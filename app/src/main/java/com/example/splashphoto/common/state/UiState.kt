package com.example.splashphoto.common.state

import androidx.annotation.StringRes

sealed class UiState<T>{

    class Init<T> :UiState<T>()

    /*
    show normal loading progress bar
     */
    class Loading<T> :UiState<T>()

    data class Success<T>(val data :T) :UiState<T>()

    data class Error<T>(val message:String?) : UiState<T>()

    data class SoftError<T>(val message: String?) : UiState<T>()

    class Empty<T> (@StringRes val msgResID :Int = 0) : UiState<T>()
    /*
    Mainly used in endless scroll of the recycler view to show bottom loading item in the list
     */

    class LoadMore<T> :UiState<T>()
}
