package com.example.project5.adapters

import androidx.recyclerview.widget.DiffUtil

class LanguagesItemDiffCallback: DiffUtil.ItemCallback<Languages>() {
    override fun areItemsTheSame(oldItem: Languages, newItem: Languages): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Languages, newItem: Languages): Boolean {
        return oldItem == newItem
    }

}