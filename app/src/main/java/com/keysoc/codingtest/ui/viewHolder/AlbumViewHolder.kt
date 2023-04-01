package com.keysoc.codingtest.ui.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keysoc.codingtest.R
import com.keysoc.codingtest.databinding.ViewHolderAlbumBinding
import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.ui.viewAdapter.AlbumsListAdapter
import com.keysoc.codingtest.util.bindGlideSrc

class AlbumViewHolder(private val binding: ViewHolderAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        albumInfo: ItunesAlbum,
        position: Int,
        isBookmarked: Boolean,
        listener: AlbumsListAdapter.AlbumViewListener
    ) {
        binding.tvArtistName.text = albumInfo.artistName
        binding.tvCollectionName.text = albumInfo.collectionName
        updateBookmarkBtn(isBookmarked)
        var bookmarkState = isBookmarked
        binding.bookmarkBtn.setOnClickListener {
            bookmarkState = !bookmarkState
            listener.onBookmarkBtnClick(position, albumInfo)
            updateBookmarkBtn(bookmarkState)
        }
        binding.imageView.bindGlideSrc(albumInfo.artworkUrl100)
        binding.executePendingBindings()
    }

    private fun updateBookmarkBtn(isBookmarked: Boolean) {
        if (isBookmarked) {
            binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark_remove)
            binding.bookmarkBtn.setColorFilter(R.color.secondary)
        } else {
            binding.bookmarkBtn.setImageResource(R.drawable.ic_bookmark_add)
            binding.bookmarkBtn.clearColorFilter()
        }
    }

    companion object {
        fun from(parent: ViewGroup): AlbumViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewHolderAlbumBinding.inflate(layoutInflater, parent, false)
            return AlbumViewHolder(binding)
        }
    }
}