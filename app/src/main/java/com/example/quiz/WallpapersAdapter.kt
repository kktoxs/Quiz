package com.example.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.model.Wallpaper

class WallpapersAdapter :
    ListAdapter<Wallpaper, WallpapersAdapter.WallpaperViewHolder>(WallpaperDiffCallback()) {
    var onWallpaperClickListener: ((Wallpaper) -> Unit)? = null

    class WallpaperViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.wallpaper_image)
        val price: TextView = view.findViewById(R.id.wallpaper_price_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.wallpaper, parent, false)
        return WallpaperViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val wallpaper = getItem(position)
        holder.image.setImageResource(wallpaper.image)
        holder.price.text = wallpaper.price.toString()

        holder.view.setOnClickListener {
            onWallpaperClickListener?.invoke(wallpaper)
        }
    }

}