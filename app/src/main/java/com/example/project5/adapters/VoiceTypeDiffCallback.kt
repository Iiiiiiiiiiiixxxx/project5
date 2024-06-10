package com.example.project5.adapters

import androidx.recyclerview.widget.DiffUtil

class VoiceTypeDiffCallback: DiffUtil.ItemCallback<VoiceType>() {
    override fun areItemsTheSame(oldItem: VoiceType, newItem: VoiceType): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VoiceType, newItem: VoiceType): Boolean {
        return oldItem == newItem
    }

}