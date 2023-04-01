package com.keysoc.codingtest.ui.viewModel

import com.keysoc.codingtest.network.ItunesGatewayHelper
import com.keysoc.codingtest.ui.base.BaseViewModel

class StyleViewModel:BaseViewModel() {
    fun getItunesAlbum(){
        disposableList.add(
            ItunesGatewayHelper.searchAlbum("jack+johnson")
        )
    }
}