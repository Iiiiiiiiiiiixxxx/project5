package com.example.project5.FolderLanguageVoices

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.project5.R
import com.example.project5.adapters.LanguagesViewHolder
import com.example.project5.databinding.LanguageVoicesBinding

class VoiceoverLanguageAdapter : ListAdapter<DataClassVoiceoverLanguage, LanguagesViewHolder>(VoiceoverLanguageDiffCallback()) {

    var onItemClickListener: ((DataClassVoiceoverLanguage, Int, List<DataClassVoiceoverLanguage>) -> Unit)? = null

    private var selectedItem: DataClassVoiceoverLanguage? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagesViewHolder {


        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.language_voices,
            parent,
            false
        )

        return LanguagesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: LanguagesViewHolder, position: Int) {
        val item = getItem(position)


        when (val binding = viewHolder.binding) {

            is LanguageVoicesBinding -> {
                if (item.imageSrc != null) {
                    binding.ImageViewLanguageVoices.setImageResource(item.imageSrc)
                }

                binding.LanguageVoices.text = item.name

                if (item.isSelected4) {
                    binding.myRadioButton4.setBackgroundResource(R.drawable.ic_checked)
                } else {
                    binding.myRadioButton4.setBackgroundResource(R.drawable.ic_unchecked)
                }

                binding.myRadioButton4.setOnClickListener {
                    onItemClickListener?.invoke(item, position, currentList)
                }
            }

        }

    }
}