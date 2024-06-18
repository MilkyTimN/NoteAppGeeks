package com.example.noteappgeeks.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteappgeeks.R
import com.example.noteappgeeks.databinding.FragmentOnBoearPagingBinding

class OnBoardPagingFragment : Fragment() {

    private var _binding: FragmentOnBoearPagingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoearPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding){
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                tvText.text = "Очень удобный функционал"
                animationView.setAnimation(R.raw.animation1)
            }
            1 -> {
                tvText.text= "Быстрый, качественный продукт"
                animationView.setAnimation(R.raw.animation2)
            }
            2 -> {
                tvText.text = "Куча функций и интересных фишек"
                animationView.setAnimation(R.raw.animation3)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}