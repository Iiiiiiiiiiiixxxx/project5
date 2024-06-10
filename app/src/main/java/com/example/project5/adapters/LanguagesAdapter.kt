package com.example.project5.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.project5.R
import com.example.project5.databinding.LanguageBinding

class LanguagesAdapter : ListAdapter<Languages, LanguagesViewHolder>(LanguagesItemDiffCallback()) {

    var onItemClickListener: ((Languages, Int, List<Languages>) -> Unit)? = null

    private var selectedItem: Languages? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagesViewHolder {


        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.language,
            parent,
            false
        )

        return LanguagesViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: LanguagesViewHolder, position: Int) {
        val item = getItem(position)


        when (val binding = viewHolder.binding) {

            is LanguageBinding -> {
                if (item.imageSrc != null) {
                    binding.iconImageView1.setImageResource(item.imageSrc)
                }

                binding.language.text = item.nameLanguage

                if (item.isSelected) {
                    binding.myRadioButton.setBackgroundResource(R.drawable.ic_checked)
                } else {
                    binding.myRadioButton.setBackgroundResource(R.drawable.ic_unchecked)
                }

                binding.myRadioButton.setOnClickListener {
                    onItemClickListener?.invoke(item, position, currentList)
                }
            }

        }

    }
}



