package com.example.project5
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project5.adapters.LanguageViewModel
import com.example.project5.databinding.FragmentScreen4Binding


class Screen4 : Fragment() {

    private var _binding: FragmentScreen4Binding? = null
    private val binding: FragmentScreen4Binding
        get() = _binding ?: throw RuntimeException("FragmentScreen4Binding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen4Binding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        OpenForm()
        initInsetsListener()


        val viewModel = ViewModelProvider(requireActivity())[LanguageViewModel::class.java]

        viewModel.language.observe(viewLifecycleOwner) {
            Log.d("ViewModel", "exit data: $it")
            binding.SourceDisplaysLanguage.text = it.nameLanguage
            it.imageSrc?.let { it1 -> binding.ImageViewSourceDisplaystTheFlag.setImageResource(it1) }

        }

        viewModel.selectedLanguage.observe(viewLifecycleOwner) {
            Log.d("ViewModel", "exit data: $it")
            binding.TextViewSubtitleStyle.text = it
            binding.ImageViewSubtitleStyle.setImageResource(R.drawable.less_than_icon)
        }

        viewModel.selectedVoiceType.observe(viewLifecycleOwner) {
            binding.TextVoic.text = it.nameVoiceType
            binding.ImageViewVoicType.setImageResource(R.drawable.less_than_icon)
        }

        viewModel.selectedTranslateSubtitlesTo.observe(viewLifecycleOwner) {
            binding.displaysTranslateSubtitlesTo.text = it.name
            it.imageSrc?.let { it1 -> binding.imageViewdisplaysTranslateSubtitlesTo.setImageResource(it1) }
        }

        viewModel.selectedVoiceoverLanguageo.observe(viewLifecycleOwner) {
            binding.displaysVoiceLanguage.text = it.name
            it.imageSrc?.let { it1 -> binding.imageViewdisplaysVoiceLanguage.setImageResource(it1) }

        }

        binding.SourceDisplaysLanguage.setOnClickListener{

            viewModel.language.observe(viewLifecycleOwner) {
                Log.d("ViewModel", "exit data: $it")
                binding.SourceDisplaysLanguage.text = it.nameLanguage
                it.imageSrc?.let { it1 -> binding.ImageViewSourceDisplaystTheFlag.setImageResource(it1) }

            }
        }

        binding.ImageViewSourceDisplaystTheFlag.setOnClickListener{
            viewModel.language.observe(viewLifecycleOwner) {
                Log.d("ViewModel", "exit data: $it")
                binding.SourceDisplaysLanguage.text = it.nameLanguage
                it.imageSrc?.let { it1 -> binding.ImageViewSourceDisplaystTheFlag.setImageResource(it1) }

            }
        }

        binding.TextViewSubtitleStyle.setOnClickListener{
            viewModel.selectedLanguage.observe(viewLifecycleOwner) {
                Log.d("ViewModel", "exit data: $it")
                binding.TextViewSubtitleStyle.text = it
                binding.ImageViewSubtitleStyle.setImageResource(R.drawable.less_than_icon)
            }
        }

        binding.displaysTranslateSubtitlesTo.setOnClickListener{
            viewModel.selectedTranslateSubtitlesTo.observe(viewLifecycleOwner) {
                binding.displaysTranslateSubtitlesTo.text = it.name
                it.imageSrc?.let { it1 -> binding.imageViewdisplaysTranslateSubtitlesTo.setImageResource(it1) }
            }
        }

        binding.imageViewdisplaysTranslateSubtitlesTo.setOnClickListener{
            viewModel.selectedTranslateSubtitlesTo.observe(viewLifecycleOwner) {
                binding.displaysTranslateSubtitlesTo.text = it.name
                it.imageSrc?.let { it1 -> binding.imageViewdisplaysTranslateSubtitlesTo.setImageResource(it1) }
            }
        }

        binding.displaysVoiceLanguage.setOnClickListener{
            viewModel.selectedVoiceoverLanguageo.observe(viewLifecycleOwner) {
                binding.displaysVoiceLanguage.text = it.name
                it.imageSrc?.let { it1 -> binding.imageViewdisplaysVoiceLanguage.setImageResource(it1) }

            }
        }

        binding.imageViewdisplaysVoiceLanguage.setOnClickListener{
            viewModel.selectedVoiceoverLanguageo.observe(viewLifecycleOwner) {
                binding.displaysVoiceLanguage.text = it.name
                it.imageSrc?.let { it1 -> binding.imageViewdisplaysVoiceLanguage.setImageResource(it1) }

            }
        }

        binding.TextVoic.setOnClickListener{
            viewModel.selectedVoiceType.observe(viewLifecycleOwner) {
                binding.TextVoic.text = it.nameVoiceType
                binding.ImageViewVoicType.setImageResource(R.drawable.less_than_icon)
            }
        }

        binding.ImageViewSubtitleStyle.setOnClickListener{
            viewModel.selectedLanguage.observe(viewLifecycleOwner) {
                Log.d("ViewModel", "exit data: $it")
                binding.TextViewSubtitleStyle.text = it
                binding.ImageViewSubtitleStyle.setImageResource(R.drawable.less_than_icon)
            }
        }

        binding.ImageViewVoicType.setOnClickListener{
            viewModel.selectedVoiceType.observe(viewLifecycleOwner) {
                binding.TextVoic.text = it.nameVoiceType
                binding.ImageViewVoicType.setImageResource(R.drawable.less_than_icon)
            }
        }


        val MainMenu = binding.MainMenu
        val cardViewTranslateSubtitles = binding.cardViewTranslateSubtitles
        val cardViewVoiceover = binding.cardViewVoiceover
        val switch1 = binding.Switch
        val switch2 = binding.Switch2
        val switch3 = binding.Switch3

        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Показываем CardView с анимацией
                MainMenu.visibility = View.VISIBLE
                MainMenu.alpha = 0f // Устанавливаем фактическое значение прозрачности в 0
                MainMenu.animate().alpha(1f).setDuration(500).start()
            } else {
                // Скрываем CardView с анимацией
                MainMenu.animate().alpha(0f).setDuration(500).withEndAction {
                    MainMenu.visibility = View.GONE
                    MainMenu.alpha = 1f // Устанавливаем фактическое значение прозрачности в 1
                }.start()
            }
        }


        switch2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Показываем CardView с анимацией
                cardViewTranslateSubtitles.visibility = View.VISIBLE
                cardViewTranslateSubtitles.alpha = 0f // Устанавливаем фактическое значение прозрачности в 0
                cardViewTranslateSubtitles.animate().alpha(1f).setDuration(500).start()
            } else {
                // Скрываем CardView с анимацией
                cardViewTranslateSubtitles.animate().alpha(0f).setDuration(500).withEndAction {
                    cardViewTranslateSubtitles.visibility = View.GONE
                    cardViewTranslateSubtitles.alpha = 1f // Устанавливаем фактическое значение прозрачности в 1
                }.start()
            }
        }

        switch3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Показываем CardView с анимацией
                cardViewVoiceover.visibility = View.VISIBLE
                cardViewVoiceover.alpha = 0f // Устанавливаем фактическое значение прозрачности в 0
                cardViewVoiceover.animate().alpha(1f).setDuration(500).start()
            } else {
                // Скрываем CardView с анимацией
                cardViewVoiceover.animate().alpha(0f).setDuration(500).withEndAction {
                    cardViewVoiceover.visibility = View.GONE
                    cardViewVoiceover.alpha = 1f // Устанавливаем фактическое значение прозрачности в 1
                }.start()
            }
        }

    }

    @SuppressLint("ResourceType")
    private fun OpenForm() {

        binding.SourceLanguage.setOnClickListener {
            val newFragment = Screen3()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.SubtitleStyle.setOnClickListener {
            val newFragment = Screen2()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.VoiceType.setOnClickListener {
            val newFragment = voice_type()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.TVtextViewTranslateSubtitlesTo.setOnClickListener {
            val newFragment = TranslateSubtitlesTo()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.TVtextViewLanguageVoices.setOnClickListener {
            val newFragment = FragmentLanguageVoices()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.SourceDisplaysLanguage.setOnClickListener {
            val newFragment = Screen3()
            newFragment.show(requireActivity().supportFragmentManager, "tag")

        }

        binding.ImageViewSourceDisplaystTheFlag.setOnClickListener {
            val newFragment = Screen3()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.TextViewSubtitleStyle.setOnClickListener {
            val newFragment = Screen2()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.ImageViewSubtitleStyle.setOnClickListener {
            val newFragment = Screen2()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.displaysTranslateSubtitlesTo.setOnClickListener {
            val newFragment = TranslateSubtitlesTo()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.imageViewdisplaysTranslateSubtitlesTo.setOnClickListener {
            val newFragment = TranslateSubtitlesTo()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.displaysVoiceLanguage.setOnClickListener {
            val newFragment = FragmentLanguageVoices()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.imageViewdisplaysVoiceLanguage.setOnClickListener {
            val newFragment = FragmentLanguageVoices()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.TextVoic.setOnClickListener {
            val newFragment = voice_type()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.ImageViewVoicType.setOnClickListener {
            val newFragment = voice_type()
            newFragment.show(requireActivity().supportFragmentManager, "tag")
        }

        binding.back.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val initialFragment = Screen1()

            fragmentTransaction.replace(R.id.fragment_container, initialFragment)

            fragmentTransaction.addToBackStack(null)

            fragmentTransaction.commit()
        }


    }

    //код для обработки системных view элементов:
    private fun initInsetsListener() {
        val btCreate = binding.btCreate
        val cardViewSubtitleSettingsWindow = binding.cvSubtitle

        val insetsListener = androidx.core.view.OnApplyWindowInsetsListener { view, insets ->
            val systemWindowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = systemWindowInsets.top,
            )
            insets
        }

        //ViewCompat.setOnApplyWindowInsetsListener(btCreate, insetsListener)
        ViewCompat.setOnApplyWindowInsetsListener(cardViewSubtitleSettingsWindow, insetsListener)
    }

}