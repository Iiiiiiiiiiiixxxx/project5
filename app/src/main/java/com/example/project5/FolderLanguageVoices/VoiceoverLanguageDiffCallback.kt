package com.example.project5.FolderLanguageVoices

import androidx.recyclerview.widget.DiffUtil

class VoiceoverLanguageDiffCallback: DiffUtil.ItemCallback<DataClassVoiceoverLanguage>() {
    override fun areItemsTheSame(oldItem: DataClassVoiceoverLanguage, newItem: DataClassVoiceoverLanguage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataClassVoiceoverLanguage, newItem: DataClassVoiceoverLanguage): Boolean {
        return oldItem == newItem
    }

}