package com.example.project5.FolderTranslateSubtitlesTo

data class DataClassTranslateSubtitlesTo(
    val id: Int,
    val name: String,
    var isSelected3: Boolean,
    val imageSrc: Int?,
    val nameIso: String

) {
    companion object {
        private var idCounter = 0

        fun create2(
            nameLanguage: String,
            isSelected: Boolean,
            imageSrc: Int?,
            nameIso: String
        ): DataClassTranslateSubtitlesTo {
            return DataClassTranslateSubtitlesTo(
                idCounter++,
                nameLanguage,
                isSelected,
                imageSrc,
                nameIso
            )
        }
    }
}