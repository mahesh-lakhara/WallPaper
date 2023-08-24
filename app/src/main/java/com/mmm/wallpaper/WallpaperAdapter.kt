package com.mmm.wallpaper

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mmm.wallpaper.databinding.PhotoitemBinding


class WallpaperAdapter : RecyclerView.Adapter<WallpaperAdapter.wallpaperHolder>() {

    lateinit var photos: ArrayList<PhotosItem>
    lateinit var context: Context

    class wallpaperHolder(itemView: PhotoitemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wallpaperHolder {
        context = parent.context
        var binding = PhotoitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return wallpaperHolder(binding)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: wallpaperHolder, position: Int) {

        holder.binding.apply {
            photos.get(position)?.src?.apply {
                Glide.with(context).load(portrait).into(imgphotos)
            }
        }

    }

    fun setPhotos(photos: List<PhotosItem>?) {
        this.photos = (photos as ArrayList<PhotosItem>?)!!
    }
}