package com.example.project5.adapters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project5.FolderLanguageVoices.DataClassVoiceoverLanguage
import com.example.project5.FolderTranslateSubtitlesTo.DataClassTranslateSubtitlesTo


class LanguageViewModel : ViewModel() {

    val language = MutableLiveData<Languages>()

    val selectedLanguage = MutableLiveData<String>()

    val selectedVoiceType = MutableLiveData<VoiceType>()

    val selectedTranslateSubtitlesTo = MutableLiveData<DataClassTranslateSubtitlesTo>()

    val selectedVoiceoverLanguageo = MutableLiveData<DataClassVoiceoverLanguage>()

}

