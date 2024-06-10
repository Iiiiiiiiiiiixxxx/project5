package com.example.project5

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.project5.adapters.LanguageViewModel
import com.example.project5.adapters.Languages
import com.example.project5.adapters.LanguagesAdapter
import com.example.project5.databinding.FragmentScreen3Binding
import com.google.android.material.animation.AnimatorSetCompat.playTogether
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale


class Screen3 : BottomSheetDialogFragment() {

    private lateinit var LanguagesAdapter: LanguagesAdapter

    private val listLanguages = mutableListOf(
        Languages.create(
            "Polski",
            false,
            imageSrc = R.drawable.kyrva,
            "pl",
        ),

        Languages.create(
            "Portugues",
            false,
            imageSrc = R.drawable.brazilianfonk,
            "pt",
        ),

        Languages.create(
            "Nederlands",
            false,
            imageSrc = R.drawable.netherlands,
            "nl",
        ),
    )

    private var _binding: FragmentScreen3Binding? = null
    private val binding: FragmentScreen3Binding
        get() = _binding ?: throw RuntimeException("FragmentScreen3Binding == null")

    private var selectedItem: Languages? = null

    private var isSearchLayoutMoved = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen3Binding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[LanguageViewModel::class.java]

        val editTextSearch1 = binding.editTextSearch1
        val imageView = binding.imageView2


        editTextSearch1.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val imageViewAnimation = ObjectAnimator.ofFloat(imageView, "translationX", 0f, -370f)
                imageViewAnimation.duration = 200

                val editTextAnimation = ObjectAnimator.ofFloat(editTextSearch1, "translationX", 0f, -370f)
                editTextAnimation.duration = 200

                AnimatorSet().apply {
                    playTogether(imageViewAnimation, editTextAnimation)
                    start()
                }
            } else {
                val imageViewAnimation = ObjectAnimator.ofFloat(imageView, "translationX", -100f, 0f)
                imageViewAnimation.duration = 200

                val editTextAnimation = ObjectAnimator.ofFloat(editTextSearch1, "translationX", -100f, 0f)
                editTextAnimation.duration = 200

                AnimatorSet().apply {
                    playTogether(imageViewAnimation, editTextAnimation)
                    start()
                }
            }
        }



        binding.cross2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.Button3.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }


        val languagesList = SystemLanguage()


        Log.d("GetCurrentLocal", "GetCurrentLocal")

        listLanguages.addAll(languagesList)

        recyclerView1(listLanguages)


        editTextSearch1.addTextChangedListener {
            val searchText = it.toString()
            filterLanguages(searchText)
        }

    }

    private fun filterLanguages(searchText: String) {

        val filteredList = mutableListOf<Languages>()
        for (language in listLanguages) {
            if (language.nameLanguage.contains(searchText, ignoreCase = true)) {
                filteredList.add(language)
            }
        }
        LanguagesAdapter.submitList(filteredList)
    }

    private fun recyclerView1(listLanguages: List<Languages>) {
        val viewModel = ViewModelProvider(requireActivity())[LanguageViewModel::class.java]
        val recyclerView = binding.RecyclerView
        with(recyclerView) {

            LanguagesAdapter = LanguagesAdapter()

            adapter = LanguagesAdapter
        }
        LanguagesAdapter.submitList(listLanguages)

        LanguagesAdapter.onItemClickListener = { item, position, currentList ->
            val newList = currentList.toMutableList()
            var selectedItem = newList.find { it.isSelected }
            if (selectedItem != null) {
                selectedItem.isSelected = false
                val selectedItemPosition = newList.indexOf(selectedItem)
                newList[selectedItemPosition] = selectedItem
            }
            item.isSelected = true
//            newList[position] = item
            selectedItem = item
            // Обновите UI
            LanguagesAdapter.notifyDataSetChanged()
            // Обновите список
            LanguagesAdapter.submitList(newList)
            // Проверьте значение selectedItem перед установкой его значения в ViewModel
            if (selectedItem != null) {
                viewModel.language.value = selectedItem
            }
        }

    }

    private fun SystemLanguage(): List<Languages> {

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
        val exitList = mutableListOf<Languages>()
        Log.d("CurrentLanguage", "$languageMap")


        for (item in languageMap) {
            val imageResource = getImageResourceForLanguage(item.key)
            exitList.add(
                Languages.create(
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