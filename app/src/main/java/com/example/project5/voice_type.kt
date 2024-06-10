package com.example.project5
import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.project5.adapters.LanguageViewModel
import com.example.project5.adapters.VoiceType
import com.example.project5.adapters.VoiceTypeAdapter
import com.example.project5.databinding.FragmentVoiceTypeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class voice_type : BottomSheetDialogFragment() {


    private val listVoiceType = mutableListOf(
        VoiceType(
            1,
            "Jeppe",
            false,
            R.raw.brue,

            ),

        VoiceType(
            2,
            "Christel",
            false,
            R.raw.uiuiu,
        ),
    )


    private lateinit var VoiceTypeAdapter: VoiceTypeAdapter

    private val mediaPlayer = MediaPlayer()

    private var _binding: FragmentVoiceTypeBinding? = null
    private val binding: FragmentVoiceTypeBinding
        get() = _binding ?: throw RuntimeException("FragmentVoiceTypeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVoiceTypeBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cross4.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.Button6.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        fun recyclerView2(listVoiceType: List<VoiceType>) {
            val recyclerView = binding.RecyclerView2
            with(recyclerView) {

                VoiceTypeAdapter = VoiceTypeAdapter()

                adapter = VoiceTypeAdapter
            }
            VoiceTypeAdapter.submitList(listVoiceType)


            val viewModel = ViewModelProvider(requireActivity())[LanguageViewModel::class.java]

            VoiceTypeAdapter.onItemClickListener = { item, position, currentList ->
                val newList = currentList.toMutableList()
                var selectedItem = newList.find { it.isSelected2 }
                if (selectedItem != null) {
                    selectedItem.isSelected2 = false
                    val selectedItemPosition = newList.indexOf(selectedItem)
                    newList[selectedItemPosition] = selectedItem
                }
                item.isSelected2 = true
                newList[position] = item
                selectedItem = item
                // Обновите UI
                VoiceTypeAdapter.notifyDataSetChanged()
                // Обновите список
                VoiceTypeAdapter.submitList(newList)
                // Проверьте значение selectedItem перед установкой его значения в ViewModel
                if (selectedItem != null) {
                    viewModel.selectedVoiceType.value = selectedItem
                    // Воспроизведите звук
                    val soundId = selectedItem.soundResId
                    MediaPlayer.create(requireContext(), soundId).start()
                }
            }
        }

        recyclerView2(listVoiceType)

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}



//VoiceTypeAdapter.onItemClickListener = { item, position, currentList ->
//    val newList = currentList.toMutableList()
//    var selectedItem = newList.find { it.isSelected2 }
//    if (selectedItem != null) {
//        selectedItem.isSelected2 = false
//        val selectedItemPosition = newList.indexOf(selectedItem)
//        newList[selectedItemPosition] = selectedItem
//    }
//    item.isSelected2 = true
//    newList[position] = item
//    selectedItem = item
//    // Обновите UI
//    VoiceTypeAdapter.notifyDataSetChanged()
//    // Обновите список
//    VoiceTypeAdapter.submitList(newList)
//    // Проверьте значение selectedItem перед установкой его значения в ViewModel
//    if (selectedItem != null) {
//        viewModel.selectedVoiceType.value = selectedItem
//    }
//}
//
//VoiceTypeAdapter.onItemClickListener = { item, position, currentList ->
//    // Получите контекст из itemView (предполагая, что это представление элемента списка)
//
//    // Получите ресурс звукового файла из ресурсов приложения
//    val soundResourceId = R.raw.brue
//
//    // Воспроизведите звук
//    val mediaPlayer = MediaPlayer.create(context, soundResourceId)
//    mediaPlayer.start()
//
//    // Ваш существующий код для обработки выбранного элемента
//    val newList = currentList.toMutableList()
//    var selectedItem = newList.find { it.isSelected2 }
//    if (selectedItem != null) {
//        selectedItem.isSelected2 = false
//        val selectedItemPosition = newList.indexOf(selectedItem)
//        newList[selectedItemPosition] = selectedItem
//    }
//    item.isSelected2 = true
//    newList[position] = item
//    selectedItem = item
//    // Обновите UI
//
//    // Обновите список
//
//    // Проверьте значение selectedItem перед установкой его значения в ViewModel
//    if (selectedItem != null) {
//        viewModel.selectedVoiceType.value = selectedItem
//    }
//}

