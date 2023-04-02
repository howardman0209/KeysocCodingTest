package com.keysoc.codingtest.ui.view.activity

import android.os.Bundle
import android.util.Log
import com.keysoc.codingtest.R
import com.keysoc.codingtest.databinding.ActivityAlbumsListBinding
import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.ui.base.MVVMActivity
import com.keysoc.codingtest.ui.viewAdapter.AlbumsListAdapter
import com.keysoc.codingtest.ui.viewModel.AlbumsListViewModel

class AlbumsListActivity : MVVMActivity<AlbumsListViewModel, ActivityAlbumsListBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchItunesAlbum {
            stopRefreshing()
        }
        viewModel.initBookmarkedCollectionIdList(applicationContext)

        val adapter = AlbumsListAdapter(
            object : AlbumsListAdapter.AlbumViewListener {
                override fun onBookmarkBtnClick(position: Int, albumInfo: ItunesAlbum) {
                    Log.d("onBookmarkBtnClick", "position: $position, collectionId: ${albumInfo.collectionId}")
                    viewModel.setBookmarkedCollectionIdList(applicationContext, albumInfo)
                }
            },
            applicationContext
        )

        viewModel.displayAlbumsList.observe(this) {
            adapter.setData(it)
        }

        binding.rvAlbums.adapter = adapter

        binding.swipeContainer.setOnRefreshListener {
            viewModel.fetchItunesAlbum(true) {
                stopRefreshing()
            }
        }

        binding.viewBookmarkedBtn.setOnClickListener {
            viewModel.isDisplayBookmark = !viewModel.isDisplayBookmark
            if (viewModel.isDisplayBookmark) {
                viewModel.displayBookmarkedAlbum()
                binding.toolbar.title = getString(R.string.label_bookmarked_albums)
                binding.viewBookmarkedBtn.text = getString(R.string.button_all)
                binding.viewBookmarkedBtn.setIconResource(R.drawable.ic_library_music)
            } else {
                viewModel.displayFetchedAlbum()
                binding.toolbar.title = getString(R.string.label_itunes_albums)
                binding.viewBookmarkedBtn.text = getString(R.string.button_bookmarked)
                binding.viewBookmarkedBtn.setIconResource(R.drawable.ic_bookmarks)
            }
        }
    }

    private fun stopRefreshing() {
        binding.swipeContainer.isRefreshing = false
    }

    override fun getViewModelInstance(): AlbumsListViewModel = AlbumsListViewModel()

    override fun setBindingData() {
        binding.view = this
        binding.viewModel = viewModel
    }

    override fun getLayoutResId(): Int = R.layout.activity_albums_list
}