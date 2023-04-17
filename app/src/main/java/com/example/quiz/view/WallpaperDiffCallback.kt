package com.example.quiz.view

import androidx.recyclerview.widget.DiffUtil
import com.example.quiz.model.Wallpaper

class WallpaperDiffCallback : DiffUtil.ItemCallback<Wallpaper>() {
    override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
        return oldItem == newItem
    }
}