package com.keysoc.codingtest.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.keysoc.codingtest.model.Bookmark
import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.network.ItunesGatewayHelper
import com.keysoc.codingtest.ui.base.BaseViewModel
import com.keysoc.codingtest.util.DEBUG
import com.keysoc.codingtest.util.PreferencesUtil

class AlbumsListViewModel : BaseViewModel() {
    val displayAlbumsList: MutableLiveData<List<ItunesAlbum>> = MutableLiveData()
    private val fetchedAlbumList: MutableList<ItunesAlbum> = mutableListOf()
    var isDisplayBookmark: Boolean = false
    private val bookmarkedCollectionIdList: MutableList<Long> = mutableListOf()

    fun fetchItunesAlbum(bySwipe: Boolean = false, stopRefreshing: () -> Unit) {
        if (!bySwipe) {
            showLoadingIndicator(true)
        }
        disposableList.add(
            ItunesGatewayHelper.searchAlbum("jack+johnson",
                onSuccess = {
                    showLoadingIndicator(false)
                    stopRefreshing.invoke()
                    fetchedAlbumList.clear()
                    fetchedAlbumList.addAll(it)
                    if (!isDisplayBookmark) {
                        displayAlbumsList.value = it
                    }
                },
                catchError = {
                    apiError.value = it.message
                }
            )
        )
    }

    fun initBookmarkedCollectionIdList(context: Context) {
        bookmarkedCollectionIdList.addAll(PreferencesUtil.getBookmark(context).albumCollectionIdList ?: emptyList())
        Log.d(DEBUG, "bookmarkedCollectionIdList: $bookmarkedCollectionIdList")
    }

    fun setBookmarkedCollectionIdList(context: Context, albumInfo: ItunesAlbum) {
        val isBookmarked = bookmarkedCollectionIdList.contains(albumInfo.collectionId)

        if (isBookmarked) {
            bookmarkedCollectionIdList.remove(albumInfo.collectionId)
        } else {
            albumInfo.collectionId?.let { bookmarkedCollectionIdList.add(it) }
        }
        val bookmark = Bookmark(
            albumCollectionIdList = bookmarkedCollectionIdList
        )
        PreferencesUtil.saveBookmark(context, bookmark)
    }

    fun displayBookmarkedAlbum() {
        val fullAlbumList = displayAlbumsList.value
        val bookmarkedAlbums = fullAlbumList?.let {
            it.filter { album -> bookmarkedCollectionIdList.contains(album.collectionId) }
        }
        Log.d(DEBUG, "bookmarkedAlbums: $bookmarkedAlbums")
        displayAlbumsList.value = bookmarkedAlbums
    }

    fun displayFetchedAlbum() {
        displayAlbumsList.value = fetchedAlbumList
    }
}