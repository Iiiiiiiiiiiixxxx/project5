package com.example.project5.FolderTranslateSubtitlesTo

import androidx.recyclerview.widget.DiffUtil


class TranslateSubtitlesToDiffCallback: DiffUtil.ItemCallback<DataClassTranslateSubtitlesTo>() {
    override fun areItemsTheSame(oldItem: DataClassTranslateSubtitlesTo, newItem: DataClassTranslateSubtitlesTo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataClassTranslateSubtitlesTo, newItem: DataClassTranslateSubtitlesTo): Boolean {
        return oldItem == newItem
    }

}