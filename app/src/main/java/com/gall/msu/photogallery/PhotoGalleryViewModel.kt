package com.gall.msu.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gall.msu.photogallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()

    private val pageSize = 50
    private val config = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setEnablePlaceholders(false)
        .build()

    val galleryItems: LiveData<PagedList<GalleryItem>> by lazy {
        val dataSourceFactory =
            object : DataSource.Factory<Int, GalleryItem>() {
                override fun create(): DataSource<Int, GalleryItem> {
                    return PhotoDataSource(viewModelScope, photoRepository)
                }
            }

        LivePagedListBuilder(dataSourceFactory, config).build()
    }
}

