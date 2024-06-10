package com.example.project5.FolderTranslateSubtitlesTo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.project5.R
import com.example.project5.adapters.LanguagesViewHolder
import com.example.project5.databinding.TranslateSubtitlesToBinding

class TranslateSubtitlesToAdapter : ListAdapter<DataClassTranslateSubtitlesTo, LanguagesViewHolder>(TranslateSubtitlesToDiffCallback()) {

    var onItemClickListener2: ((DataClassTranslateSubtitlesTo, Int, List<DataClassTranslateSubtitlesTo>) -> Unit)? = null

    private var selectedItem: DataClassTranslateSubtitlesTo? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagesViewHolder {


        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.translate_subtitles_to,
            parent,
            false
        )

        return LanguagesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: LanguagesViewHolder, position: Int) {
        val item = getItem(position)


        when (val binding = viewHolder.binding) {

            is TranslateSubtitlesToBinding -> {
                if (item.imageSrc != null) {
                    binding.ImageViewTranslateSubtitlesTo.setImageResource(item.imageSrc)
                }

                binding.TextViewTranslateSubtitlesTo.text = item.name

                if (item.isSelected3) {
                    binding.myRadioButton3.setBackgroundResource(R.drawable.ic_checked)
                } else {
                    binding.myRadioButton3.setBackgroundResource(R.drawable.ic_unchecked)
                }

                binding.myRadioButton3.setOnClickListener {
                    onItemClickListener2?.invoke(item, position, currentList)
                }
            }

        }

    }
}