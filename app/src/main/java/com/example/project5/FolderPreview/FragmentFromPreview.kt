package com.example.project5

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.project5.FolderPreview.ProjectItem
import com.example.project5.FolderPreview.ProjectListAdapter
import com.example.project5.databinding.FragmentFromVideoBinding

class FragmentFromPreview : Fragment() {

    private lateinit var etProjectName: EditText
    private lateinit var ivClear: ImageView

    private lateinit var projectListAdapter: ProjectListAdapter

    private var _binding: FragmentFromVideoBinding? = null
    private val binding: FragmentFromVideoBinding
        get() = _binding ?: throw RuntimeException("FragmentFromVideoBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFromVideoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test_preview)
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.test_preview2)

        val listPreview = mutableListOf(
            ProjectItem(
                id = 0,
                name = "Новый проект",
                date = "20.02.2024",
                duration = "00:32",
                preview = bitmap,
                content = "Ваш контент",
                taskId = null,
                resultSubtitles = null
            ),

            ProjectItem(
                id = 1,
                name = "Новый проект2",
                date = "21.02.2024",
                duration = "00:42",
                preview = bitmap2,
                content = "Ваш контент",
                taskId = null,
                resultSubtitles = null
            ),
        )
        setupRecyclerView(listPreview)


    }

    private fun setupRecyclerView(listPreview: List<ProjectItem>) {
        val rvPreview = binding.rvPreview
        projectListAdapter = ProjectListAdapter()

        rvPreview.adapter = projectListAdapter
        projectListAdapter.submitList(listPreview)

        projectListAdapter.onProjectItemClickListener = { projectItem ->
            // Обработка клика на элемент списка
        }

        projectListAdapter.onMoreButtonClickListener = { projectItem, view ->
            showPopupWindow(view, projectItem)
        }

    }

    private fun showPopupMenu(view: View, projectItem: ProjectItem) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_rename -> {
                    showRenameDialog(projectItem)
                    true
                }
                R.id.action_share -> {
                    shareProject(projectItem)
                    true
                }
                R.id.action_delete -> {
                    showDeleteDialog(projectItem)
                    true
                }
                else -> false
            }
        }

        // Adjust the position of the popup menu
        view.post {
            popupMenu.show()
            try {
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popupMenu)
                menu.javaClass.getDeclaredMethod("setHorizontalOffset", Int::class.java)
                    .invoke(menu, -view.width / 2)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun showRenameDialog(projectItem: ProjectItem) {

        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_rename_project, null)
        val etProjectName = dialogView.findViewById<EditText>(R.id.etProjectName)

        etProjectName.setText(projectItem.name)

        val dialog = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setView(dialogView)
            .create()

        dialogView.findViewById<CardView>(R.id.btnOk).setOnClickListener {
            val newName = etProjectName.text.toString()
            renameProject(projectItem, newName)
            dialog.dismiss()
        }

        dialogView.findViewById<CardView>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

        ivClear = dialogView.findViewById(R.id.ivClear)

        ivClear.setOnClickListener {
            etProjectName.text.clear()
        }
    }

    private fun renameProject(projectItem: ProjectItem, newName: String) {
        val updatedList = projectListAdapter.currentList.toMutableList()
        val index = updatedList.indexOfFirst { it.id == projectItem.id }
        if (index != -1) {
            updatedList[index] = updatedList[index].copy(name = newName)
            projectListAdapter.submitList(updatedList.toList())
        }
    }

    private fun shareProject(projectItem: ProjectItem) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "Название проекта: ${projectItem.name}\nДата: ${projectItem.date}\nДлительность: ${projectItem.duration}"
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Поделиться проектом"))
    }

    private fun showDeleteDialog(projectItem: ProjectItem) {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_delete_project, null)

        val dialog = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setView(dialogView)
            .create()

        dialogView.findViewById<CardView>(R.id.btnDelete).setOnClickListener {
            deleteProject(projectItem)
            dialog.dismiss()
        }

        dialogView.findViewById<CardView>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun deleteProject(projectItem: ProjectItem) {
        val updatedList = projectListAdapter.currentList.toMutableList()
        val index = updatedList.indexOfFirst { it.id == projectItem.id }
        if (index != -1) {
            updatedList.removeAt(index)
            projectListAdapter.submitList(updatedList.toList())
        }
    }

    private fun showPopupWindow(view: View, projectItem: ProjectItem) {
        val inflater = LayoutInflater.from(requireContext())
        val popupView = inflater.inflate(R.layout.popup_menu_layout, null)

        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popupWindow.showAsDropDown(view, -view.width / 2, 0)

        popupView.findViewById<View>(R.id.action_rename).setOnClickListener {
            showRenameDialog(projectItem)
            popupWindow.dismiss()
        }
        popupView.findViewById<View>(R.id.action_share).setOnClickListener {
            shareProject(projectItem)
            popupWindow.dismiss()
        }
        popupView.findViewById<View>(R.id.action_delete).setOnClickListener {
            showDeleteDialog(projectItem)
            popupWindow.dismiss()
        }

        popupWindow.setOnDismissListener {
            val parent = view.parent as View
            val ivMore = parent.findViewById<ImageView>(R.id.ivMore)
            val ivClose = parent.findViewById<ImageView>(R.id.ivClose)
            ivMore.visibility = View.VISIBLE
            ivClose.visibility = View.GONE
        }
    }

}