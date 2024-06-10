package com.example.project5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.ViewModelProvider
import com.example.project5.adapters.LanguageViewModel
import com.example.project5.adapters.Languages
import com.example.project5.databinding.FragmentScreen2Binding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class Screen2 : BottomSheetDialogFragment() {

    private var selectedItem: Languages? = null

    private var _binding: FragmentScreen2Binding? = null
    private val binding: FragmentScreen2Binding
        get() = _binding ?: throw RuntimeException("FragmentScreen2Binding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cross1.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.Button2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        val viewModel = ViewModelProvider(requireActivity())[LanguageViewModel::class.java]

        val radioGroup = view.findViewById<RadioGroup>(R.id.rgSelect)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.normal -> viewModel.selectedLanguage.value = "Классический"
                R.id.active -> viewModel.selectedLanguage.value = "Динамический"
                R.id.simple -> viewModel.selectedLanguage.value = "Броский"
            }
        }

    }
}








