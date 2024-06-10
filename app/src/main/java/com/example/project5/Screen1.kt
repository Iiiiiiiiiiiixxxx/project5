package com.example.project5

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import com.example.project5.databinding.FragmentScreen1Binding


class Screen1 : Fragment() {

    private var clickCount = 0
    private var _binding: FragmentScreen1Binding? = null
    private val binding: FragmentScreen1Binding
        get() = _binding ?: throw RuntimeException("FragmentScreen1Binding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen1Binding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val videoView = binding.video
        val videoUri = Uri.parse("android.resource://com.example.project5/" + R.raw.ssst)
        videoView.setVideoURI(videoUri)
        videoView.start()

        val FourthCircle = binding.FourthCircle
        val SecondRound = binding.SecondRound
        val FifthCircle = binding.FifthCircle

        binding.Button1.setOnClickListener {
            clickCount++

            if (clickCount == 1) {

                FourthCircle.visibility = View.VISIBLE
                SecondRound.visibility = View.GONE
                FifthCircle.visibility = View.VISIBLE

                binding.TextViewFirstText.setText(R.string.text4)
                binding.TextViewSecondText.setText(R.string.text5)

            } else if (clickCount == 2) {
                navigateToSecondFragment2()
            } else if (clickCount == 3) {
                navigateToSecondFragment2()
            }
        }
    }

    private fun navigateToSecondFragment() {

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val initialFragment = Screen1()

        fragmentTransaction.replace(R.id.fragment_container, initialFragment)

        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }


    private fun navigateToSecondFragment2() {

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val initialFragment = Screen4()

        fragmentTransaction.replace(R.id.fragment_container, initialFragment)

        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }
}