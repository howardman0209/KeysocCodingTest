package com.keysoc.codingtest.ui.view.activity

import android.os.Bundle
import android.util.Log
import com.keysoc.codingtest.R
import com.keysoc.codingtest.databinding.ActivityAlbumsListBinding
import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.ui.base.MVVMActivity
import com.keysoc.codingtest.ui.viewAdapter.AlbumsListAdapter
import com.keysoc.codingtest.ui.viewModel.AlbumsListViewModel
import com.keysoc.codingtest.util.DEBUG

class AlbumsListActivity : MVVMActivity<AlbumsListViewModel, ActivityAlbumsListBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getItunesAlbum()

        val adapter = AlbumsListAdapter(
            object : AlbumsListAdapter.AlbumViewListener {
                override fun onBookmarkBtnClick(position: Int, albumInfo: ItunesAlbum) {
                    Log.d(
                        "onBookmarkBtnClick",
                        "position: $position, collectionId: ${albumInfo.collectionId}"
                    )
                }
            }
        )

        viewModel.albumsList.observe(this) {
            //stop loading ui
            showLoadingIndicator(false)
            binding.swipeContainer.isRefreshing = false

            if (it.isNotEmpty()) {
                adapter.setData(it)
            }
        }

        binding.rvAlbums.adapter = adapter

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getItunesAlbum(true)
        }
    }

    override fun getViewModelInstance(): AlbumsListViewModel = AlbumsListViewModel()

    override fun setBindingData() {
        binding.view = this
        binding.viewModel = viewModel
    }

    override fun getLayoutResId(): Int = R.layout.activity_albums_list
}