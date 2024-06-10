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
import com.example.project5.FolderLanguageVoices.DataClassVoiceoverLanguage
import com.example.project5.FolderLanguageVoices.VoiceoverLanguageAdapter
import com.example.project5.adapters.LanguageViewModel
import com.example.project5.databinding.FragmentLanguageVoicesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class FragmentLanguageVoices : BottomSheetDialogFragment() {


    private val listLanguageVoices = mutableListOf(
        DataClassVoiceoverLanguage.create3(
            "Polski",
            false,
            imageSrc = R.drawable.kyrva,
            "pl",
        ),

        DataClassVoiceoverLanguage.create3(
            "Portugues",
            false,
            imageSrc = R.drawable.brazilianfonk,
            "pt",
        ),

        DataClassVoiceoverLanguage.create3(
            "Nederlands",
            false,
            imageSrc = R.drawable.netherlands,
            "nl",
        ),

        )

    private var selectedItem: DataClassVoiceoverLanguage? = null

    private lateinit var VoiceoverLanguageAdapter: VoiceoverLanguageAdapter

    private var isSearchLayoutMoved = false

    private var _binding: FragmentLanguageVoicesBinding? = null
    private val binding: FragmentLanguageVoicesBinding
        get() = _binding ?: throw RuntimeException("FragmentLanguageVoicesBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageVoicesBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextSearch2 = binding.editTextSearch2
        val searchLayout = binding.searchLayout2

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
        editTextSearch2.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // Начинаем анимацию при нажатии на EditText
                slideLeftAnim.start()
                // Запрашиваем фокус на EditText для открытия клавиатуры
                editTextSearch2.requestFocus()
                true // Возвращаем true, чтобы обработать событие нажатия на EditText
            } else {
                false // Возвращаем false для передачи события нажатия дальше
            }
        }

        binding.cross5.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.ButtonLanguageVoices.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        val LanguageVoicesList = SystemLanguage()

        listLanguageVoices.addAll(LanguageVoicesList)

        recyclerView4(listLanguageVoices)

        editTextSearch2.addTextChangedListener {
            val searchText = it.toString()
            filterLanguages3(searchText)
        }

    }

    private fun filterLanguages3(searchText: String) {

        val filteredList = mutableListOf<DataClassVoiceoverLanguage>()
        for (VoiceoverLanguag in listLanguageVoices) {
            if (VoiceoverLanguag.name.contains(searchText, ignoreCase = true)) {
                filteredList.add(VoiceoverLanguag)
            }
        }
        VoiceoverLanguageAdapter.submitList(filteredList)
    }

    private fun recyclerView4(listLanguageVoices: List<DataClassVoiceoverLanguage>) {
        val viewModel = ViewModelProvider(requireActivity())[LanguageViewModel::class.java]
        val recyclerView = binding.RecyclerViewLanguageVoices
        with(recyclerView) {

            VoiceoverLanguageAdapter = VoiceoverLanguageAdapter()

            adapter = VoiceoverLanguageAdapter
        }
        VoiceoverLanguageAdapter.submitList(listLanguageVoices)


        VoiceoverLanguageAdapter.onItemClickListener = { item, position, currentList ->
            val newList = currentList.toMutableList()
            var selectedItem = newList.find { it.isSelected4 }
            if (selectedItem != null) {
                selectedItem.isSelected4 = false
                val selectedItemPosition = newList.indexOf(selectedItem)
                newList[selectedItemPosition] = selectedItem
            }
            item.isSelected4 = true
//            newList[position] = item
            selectedItem = item
            // Обновите UI
            VoiceoverLanguageAdapter.notifyDataSetChanged()
            // Обновите список
            VoiceoverLanguageAdapter.submitList(newList)
            // Проверьте значение selectedItem перед установкой его значения в ViewModel
            if (selectedItem != null) {
                viewModel.selectedVoiceoverLanguageo.value = selectedItem
            }
        }
    }

    private fun SystemLanguage(): List<DataClassVoiceoverLanguage> {

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
        val exitList = mutableListOf<DataClassVoiceoverLanguage>()
        Log.d("CurrentLanguage", "$languageMap")


        for (item in languageMap) {
            val imageResource = getImageResourceForLanguage(item.key)
            exitList.add(
                DataClassVoiceoverLanguage.create3(
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