package com.example.noteappgeeks.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteappgeeks.R
import com.example.noteappgeeks.databinding.FragmentOnBoardBinding
import com.example.noteappgeeks.ui.adapters.OnBoardViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {

    private var _binding: FragmentOnBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setUpListener()
    }


    private fun init() {
        binding.viewpager2.adapter = OnBoardViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewpager2) { tab, postition ->
        }.attach()
    }

    private fun setUpListener() = with(binding.viewpager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.tvStart.visibility = View.VISIBLE
                    binding.tvSkip.visibility = View.INVISIBLE
                } else {
                    binding.tvSkip.visibility = View.VISIBLE
                    binding.tvStart.visibility = View.INVISIBLE
                    binding.tvSkip.setOnClickListener {
                        setCurrentItem(currentItem + 2, true)
                    }
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}