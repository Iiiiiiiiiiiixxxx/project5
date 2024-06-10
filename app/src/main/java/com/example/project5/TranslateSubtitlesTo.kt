package com.example.project5

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.project5.FolderTranslateSubtitlesTo.DataClassTranslateSubtitlesTo
import com.example.project5.FolderTranslateSubtitlesTo.TranslateSubtitlesToAdapter
import com.example.project5.adapters.LanguageViewModel
import com.example.project5.databinding.FragmentTranslateSubtitlesToBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class TranslateSubtitlesTo : BottomSheetDialogFragment() {


   private val listTranslateSubtitlesTo = mutableListOf(
        DataClassTranslateSubtitlesTo.create2(
            "Polski",
            false,
            imageSrc = R.drawable.kyrva,
            "pl",
        ),

        DataClassTranslateSubtitlesTo.create2(
            "Portugues",
            false,
            imageSrc = R.drawable.brazilianfonk,
            "pt",
        ),

        DataClassTranslateSubtitlesTo.create2(
            "Nederlands",
            false,
            imageSrc = R.drawable.netherlands,
            "nl",
        ),

        )

    private var selectedItem: DataClassTranslateSubtitlesTo? = null

    private lateinit var TranslateSubtitlesToAdapter: TranslateSubtitlesToAdapter

    private var isSearchLayoutMoved = false

    private var _binding: FragmentTranslateSubtitlesToBinding? = null
    private val binding: FragmentTranslateSubtitlesToBinding
        get() = _binding ?: throw RuntimeException("FragmentTranslateSubtitlesToBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslateSubtitlesToBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextSearch3 = binding.editTextSearch3
        val searchLayout = binding.searchLayout3

// Получаем ширину экрана устройства
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()

// Создаем анимацию ValueAnimator для перемещения влево
        val slideLeftAnim = ValueAnimator.ofFloat(0f, -screenWidth / 3)

// Устанавливаем обновитель для анимации, который будет обновлять значение смещения элементов при изменении анимации
        slideLeftAnim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            val maxTranslation = -screenWidth / 3
            val translationX = if (value < maxTranslation) maxTranslation else value
            searchLayout.translationX = translationX
        }

// Устанавливаем продолжительность анимации
        slideLeftAnim.duration = 200 // Продолжительность анимации в миллисекундах

// Обработчик события нажатия на EditText
        editTextSearch3.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Начинаем анимацию при нажатии на EditText
                slideLeftAnim.start()
                // Запрашиваем фокус на EditText для открытия клавиатуры
                editTextSearch3.requestFocus()
                true // Возвращаем true, чтобы обработать событие нажатия на EditText
            } else {
                false // Возвращаем false для передачи события нажатия дальше
            }
        }

        binding.cross3.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.ButtonTranslateSubtitlesTo.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        val TranslateSubtitlesToList = SystemLanguage()



        listTranslateSubtitlesTo.addAll(TranslateSubtitlesToList)

        recyclerView3(listTranslateSubtitlesTo)

        editTextSearch3.addTextChangedListener {
            val searchText = it.toString()
            filterLanguages2(searchText)
        }

    }

    private fun filterLanguages2(searchText: String) {

        val filteredList = mutableListOf<DataClassTranslateSubtitlesTo>()
        for (TranslateSubtitlesTo in listTranslateSubtitlesTo) {
            if (TranslateSubtitlesTo.name.contains(searchText, ignoreCase = true)) {
                filteredList.add(TranslateSubtitlesTo)
            }
        }
        TranslateSubtitlesToAdapter.submitList(filteredList)
    }

    private fun recyclerView3(listTranslateSubtitlesTo: List<DataClassTranslateSubtitlesTo>) {
        val viewModel = ViewModelProvider(requireActivity())[LanguageViewModel::class.java]
        val recyclerView = binding.RecyclerViewButtonTranslateSubtitlesTo
        with(recyclerView) {

            TranslateSubtitlesToAdapter = TranslateSubtitlesToAdapter()

            adapter = TranslateSubtitlesToAdapter
        }
        TranslateSubtitlesToAdapter.submitList(listTranslateSubtitlesTo)


        TranslateSubtitlesToAdapter.onItemClickListener2 = { item, position, currentList ->
            val newList = currentList.toMutableList()
            var selectedItem = newList.find { it.isSelected3 }
            if (selectedItem != null) {
                selectedItem.isSelected3 = false
                val selectedItemPosition = newList.indexOf(selectedItem)
                newList[selectedItemPosition] = selectedItem
            }
            item.isSelected3 = true
//            newList[position] = item
            selectedItem = item
            // Обновите UI
            TranslateSubtitlesToAdapter.notifyDataSetChanged()
            // Обновите список
            TranslateSubtitlesToAdapter.submitList(newList)
            // Проверьте значение selectedItem перед установкой его значения в ViewModel
            if (selectedItem != null) {
                viewModel.selectedTranslateSubtitlesTo.value = selectedItem
            }
        }
    }

    private fun SystemLanguage(): List<DataClassTranslateSubtitlesTo> {

        val languageMap: MutableMap<String, String> = HashMap()
        val locales2: LocaleList
        val configuration2 = this.resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locales2 = configuration2.locales
            for (i in 0 until locales2.size()) {
                languageMap[locales2[i].language] = locales2[i].getDisplayLanguage(locales2[i])
            }
        } else {
            val locale = Locale.getDefault()
            languageMap[locale.language] = locale.getDisplayLanguage(locale)
        }
        val exitList = mutableListOf<DataClassTranslateSubtitlesTo>()
        Log.d("CurrentLanguage", "$languageMap")


        for (item in languageMap) {
            val imageResource = getImageResourceForLanguage(item.key)
            exitList.add(
                DataClassTranslateSubtitlesTo.create2(
                    item.value,
                    false,
                    imageSrc = imageResource,
                    item.key,
                )
            )
        }
        return exitList
    }

    private fun getImageResourceForLanguage(languageCode: String): Int {
        val languageImageMap = mapOf(
            "ru" to R.drawable.vodka,
            "en" to R.drawable.us,
        )
        return languageImageMap[languageCode] ?: R.drawable.xz
    }
}


