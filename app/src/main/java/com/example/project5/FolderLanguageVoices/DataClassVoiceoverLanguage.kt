package com.example.project5.FolderLanguageVoices

data class DataClassVoiceoverLanguage(
    val id: Int,
    val name: String,
    var isSelected4: Boolean,
    val imageSrc: Int?,
    val nameIso: String

) {
    companion object {
        private var idCounter = 0

        fun create3(
            nameLanguage: String,
            isSelected: Boolean,
            imageSrc: Int?,
            nameIso: String
        ): DataClassVoiceoverLanguage {
            return DataClassVoiceoverLanguage(
                idCounter++,
                nameLanguage,
                isSelected,
                imageSrc,
                nameIso
            )
        }
    }
}