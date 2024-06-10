package com.example.project5.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.project5.R
import com.example.project5.databinding.LanguageBinding
import com.example.project5.databinding.VoiceTypeBinding


class VoiceTypeAdapter : ListAdapter<VoiceType, LanguagesViewHolder>(VoiceTypeDiffCallback()) {

    private var selectedItem: Languages? = null

    var onItemClickListener: ((VoiceType, Int, List<VoiceType>) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagesViewHolder {


        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.voice_type,
            parent,
            false
        )

        return LanguagesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: LanguagesViewHolder, position: Int) {
        val item = getItem(position)


        when (val binding = viewHolder.binding) {
            is VoiceTypeBinding -> {
                binding.voic.text = item.nameVoiceType

                if (item.isSelected2) {
                    binding.myRadioButton2.setBackgroundResource(R.drawable.ic_checked)
                } else {
                    binding.myRadioButton2.setBackgroundResource(R.drawable.ic_unchecked)
                }
                binding.myRadioButton2.setOnClickListener {
                    onItemClickListener?.invoke(item, position, currentList)
                }

            }

        }

    }
}



