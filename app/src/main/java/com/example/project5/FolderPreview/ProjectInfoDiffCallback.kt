package com.example.project5.FolderPreview

import androidx.recyclerview.widget.DiffUtil

class ProjectInfoDiffCallback : DiffUtil.ItemCallback<ProjectItem>() {

    override fun areItemsTheSame(oldItem: ProjectItem, newItem: ProjectItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProjectItem, newItem: ProjectItem): Boolean {
        return oldItem == newItem
    }

}