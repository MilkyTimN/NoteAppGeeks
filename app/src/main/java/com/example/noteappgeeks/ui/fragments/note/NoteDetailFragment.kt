package com.example.noteappgeeks.ui.fragments.note

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappgeeks.App
import com.example.noteappgeeks.data.models.NoteModel
import com.example.noteappgeeks.data.models.enums.NoteColorEnum
import com.example.noteappgeeks.databinding.FragmentNoteDetailBinding
import java.time.LocalDateTime

class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!
    private var noteColorEnum: NoteColorEnum = NoteColorEnum.BLACK
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val itemId = args.noteId
        setupListeners()
        show()
//        getData(itemId)
    }

    private fun setupListeners() {
        binding.tvReady.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                App().getInstance()?.noteDao()
                    ?.insertNote(NoteModel(title, description, noteColorEnum, LocalDateTime.now()))
            }
            findNavController().navigateUp()
        }

        binding.viewBlackColor.setOnClickListener {
            binding.radioBlack.visibility = View.VISIBLE
            binding.radioWhite.visibility = View.INVISIBLE
            binding.radioRed.visibility = View.INVISIBLE
            noteColorEnum = NoteColorEnum.BLACK
        }

        binding.viewWhiteColor.setOnClickListener {
            binding.radioWhite.visibility = View.VISIBLE
            binding.radioBlack.visibility = View.INVISIBLE
            binding.radioRed.visibility = View.INVISIBLE
            noteColorEnum = NoteColorEnum.WHITE
        }

        binding.viewRedColor.setOnClickListener {
            binding.radioRed.visibility = View.VISIBLE
            binding.radioBlack.visibility = View.INVISIBLE
            binding.radioWhite.visibility = View.INVISIBLE
            noteColorEnum = NoteColorEnum.RED
        }
    }

//    private fun getData(itemId: Int) {
//        val note: NoteModel? = App().getInstance()?.noteDao()?.getNoteModel(itemId)
//        binding.etTitle.text = note?.title
//    }

    private fun show() {
        binding.etTitle.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.tvReady.visibility = if (s.isNullOrEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        })

        binding.etDescription.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.tvReady.visibility = if (s.isNullOrEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}