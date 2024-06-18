package com.example.noteappgeeks.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteappgeeks.R
import com.example.noteappgeeks.databinding.FragmentNoteBinding
import com.example.noteappgeeks.utils.SharedPreference


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupListeners()
    }

//    private fun setupListeners() = with(binding) {
//        val sharedPreference = SharedPreference()
//        sharedPreference.unit(requireContext())
//
//        btnSave.setOnClickListener {
//            val et = etTitle.text.toString()
//            sharedPreference.title = et
//            tvSave.text = et
//        }
//
//        tvSave.text = sharedPreference.title
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}