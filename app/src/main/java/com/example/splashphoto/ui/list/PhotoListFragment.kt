package com.example.splashphoto.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splashphoto.common.state.UiState
import com.example.splashphoto.common.views.EndLessScrollListener
import com.example.splashphoto.databinding.FragmentPhotoListBinding
import com.example.splashphoto.domain.model.Ad
import com.example.splashphoto.domain.model.Photo
import com.example.splashphoto.domain.model.PhotoConst
import com.example.splashphoto.ui.viewModels.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class PhotoListFragment : Fragment() , PhotosAdapter.PhotoClickListener{

    private var binding : FragmentPhotoListBinding? = null
    private val navController : NavController by lazy {findNavController()}
    private val viewModel  by viewModels<PhotosViewModel>()
    private lateinit var photosAdapter: PhotosAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoListBinding.inflate(layoutInflater,container,false)

      // Log.e("fragment","it.toString()")
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
        setUpPhotosRecyclerView()
       observeState()

    }

    private fun observeState() {

       viewModel.photoListState.observe(viewLifecycleOwner){
           Log.e("fragment",it.toString())
           Timber.tag("state").e(it.toString())
        showLoading(it is UiState.Loading || it is UiState.LoadMore)
           showEmptyView(it is UiState.Empty)
           when(it){
               is UiState.Success -> photosAdapter.submitList(it.data)
               else -> Unit
           }
       }
    }

    private fun setUpPhotosRecyclerView() = binding?.rvPhotos?.apply {
        layoutManager = LinearLayoutManager(context).also { linearLayoutManager = it }
        setHasFixedSize(true)
        photosAdapter = PhotosAdapter(this@PhotoListFragment)
        adapter = photosAdapter

        addOnScrollListener(object : EndLessScrollListener(linearLayoutManager, PhotoConst.pageSize ){
            override fun onLoadMore() = viewModel.loadPhotos()

            override fun isLastPage(): Boolean = viewModel.isLastPage

            override fun isLoading(): Boolean = viewModel.isLoading

        })

    }

    private fun setClickListeners() = binding?.apply {
        btnTryAgain.setOnClickListener{
            viewModel.apply {
                resetList()
                loadPhotos()
            }
        }
    }

    private fun showLoading( isLoading :Boolean) = binding?.progressBarLoading?.apply {
       this.isVisible = isLoading
    }

    private fun showEmptyView( isVisible :Boolean) = binding?.progressBarLoading?.apply {
        this.isVisible = isVisible
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onPhotoClick(position: Int, photo: Photo) {

    }

    override fun onAdClick(position: Int, ad: Ad) {

    }


}