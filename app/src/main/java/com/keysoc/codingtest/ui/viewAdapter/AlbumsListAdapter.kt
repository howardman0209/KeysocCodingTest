package com.keysoc.codingtest.ui.viewAdapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.ui.viewHolder.AlbumViewHolder
import com.keysoc.codingtest.util.PreferencesUtil

class AlbumsListAdapter(private val listener: AlbumViewListener, val context: Context) :
    RecyclerView.Adapter<AlbumViewHolder>() {
    private val albumsList: ArrayList<ItunesAlbum> = arrayListOf()

    interface AlbumViewListener {
        fun onBookmarkBtnClick(position: Int, albumInfo: ItunesAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder.from(parent)

    override fun getItemCount(): Int = albumsList.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val isBookmarked = PreferencesUtil.getBookmark(context).albumCollectionIdList?.contains(albumsList[position].collectionId) ?: false
        holder.bind(albumsList[holder.adapterPosition], holder.adapterPosition, isBookmarked, listener)
    }

    fun setData(dataList: List<ItunesAlbum>) {
        albumsList.clear()
        albumsList.addAll(dataList)
        notifyDataSetChanged()
    }
}