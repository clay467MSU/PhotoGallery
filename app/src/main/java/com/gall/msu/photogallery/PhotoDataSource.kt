package com.gall.msu.photogallery

import androidx.paging.PageKeyedDataSource
import com.gall.msu.photogallery.api.GalleryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PhotoDataSource(
    private val scope: CoroutineScope,
    private val repository: PhotoRepository
) : PageKeyedDataSource<Int, GalleryItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GalleryItem>
    ) {
        scope.launch {
            try {
                val page = 1 // Initial page
                val pageSize = params.requestedLoadSize
                val photos = repository.fetchPhotos(page, pageSize)
                callback.onResult(photos, null, page + 1)
            } catch (ex: Exception) {
                // Handle exception
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GalleryItem>
    ) {
        scope.launch {
            try {
                val page = params.key
                val pageSize = params.requestedLoadSize
                val photos = repository.fetchPhotos(page, pageSize)
                callback.onResult(photos, page + 1)
            } catch (ex: Exception) {
                // Handle exception
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GalleryItem>) {
        TODO("Not yet implemented")
    }

}

