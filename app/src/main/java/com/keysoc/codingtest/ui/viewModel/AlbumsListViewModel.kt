package com.keysoc.codingtest.ui.viewModel

import androidx.lifecycle.MutableLiveData
import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.network.ItunesGatewayHelper
import com.keysoc.codingtest.ui.base.BaseViewModel

class AlbumsListViewModel : BaseViewModel() {
    val albumsList: MutableLiveData<List<ItunesAlbum>> = MutableLiveData()
    fun getItunesAlbum(bySwipe: Boolean = false) {
        if (!bySwipe) {
            showLoadingIndicator(true)
        }
        disposableList.add(
            ItunesGatewayHelper.searchAlbum("jack+johnson", liveData = albumsList)
        )
    }
}