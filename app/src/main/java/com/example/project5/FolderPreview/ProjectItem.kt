package com.example.project5.FolderPreview

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class ProjectItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "duration") val duration: String,
    @ColumnInfo(name = "preview") val preview: Bitmap?,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "task_id") val taskId: String?,
    @ColumnInfo(name = "result") val resultSubtitles: String?,
)