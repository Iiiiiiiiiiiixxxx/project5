package com.example.project5.FolderPreview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.project5.R
import com.example.project5.databinding.ProjectItemBinding

class ProjectListAdapter : ListAdapter<ProjectItem, ProjectItemViewHolder>(ProjectInfoDiffCallback()) {

    var onProjectItemClickListener: ((ProjectItem) -> Unit)? = null
    var onMoreButtonClickListener: ((ProjectItem, View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectItemViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.project_item,
            parent,
            false
        )
        return ProjectItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectItemViewHolder, position: Int) {
        val item = getItem(position)

        when (val binding = holder.binding) {
            is ProjectItemBinding -> {
                with(binding) {
                    tvNameProject.text = item.name
                    tvDateProject.text = item.date
                    ivPreview.setImageBitmap(item.preview)
                    tvDuration.text = item.duration

                    root.setOnClickListener {
                        onProjectItemClickListener?.invoke(item)
                    }

                    ivMore.setOnClickListener {
                        ivMore.visibility = View.GONE
                        ivClose.visibility = View.VISIBLE
                        onMoreButtonClickListener?.invoke(item, ivMore)
                    }

                    ivClose.setOnClickListener {
                        ivMore.visibility = View.VISIBLE
                        ivClose.visibility = View.GONE
                    }
                }
            }
        }
    }
}
