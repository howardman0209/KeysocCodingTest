package com.keysoc.codingtest.ui.viewAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keysoc.codingtest.model.ItunesAlbum
import com.keysoc.codingtest.ui.viewHolder.AlbumViewHolder

class AlbumsListAdapter(private val listener: AlbumViewListener) :
    RecyclerView.Adapter<AlbumViewHolder>() {
    private val albumsList: ArrayList<ItunesAlbum> = arrayListOf()

    interface AlbumViewListener {
        fun onBookmarkBtnClick(position: Int, albumInfo: ItunesAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder.from(parent)

    override fun getItemCount(): Int = albumsList.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albumsList[holder.adapterPosition], holder.adapterPosition, false, listener)
    }

    fun setData(dataList: List<ItunesAlbum>) {
        albumsList.clear()
        albumsList.addAll(dataList)
        notifyDataSetChanged()
    }
}