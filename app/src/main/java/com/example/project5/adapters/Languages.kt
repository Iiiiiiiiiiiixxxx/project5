package com.example.project5.adapters

data class Languages(
    val id: Int,
    val nameLanguage: String,
    var isSelected: Boolean,
    val imageSrc: Int?,
    val nameIso: String

) {
    companion object {
        private var idCounter = 0

        fun create(
            nameLanguage: String,
            isSelected: Boolean,
            imageSrc: Int?,
            nameIso: String
        ): Languages {
            return Languages(
                idCounter++,
                nameLanguage,
                isSelected,
                imageSrc,
                nameIso
            )
        }
    }
}

