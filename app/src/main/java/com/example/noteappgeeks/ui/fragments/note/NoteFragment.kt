package com.example.noteappgeeks.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappgeeks.App
import com.example.noteappgeeks.R
import com.example.noteappgeeks.data.models.NoteModel
import com.example.noteappgeeks.databinding.FragmentNoteBinding
import com.example.noteappgeeks.interfaces.Clickable
import com.example.noteappgeeks.ui.adapters.NoteAdapter
import com.example.noteappgeeks.utils.SharedPreference


class NoteFragment : Fragment(), Clickable {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private val noteAdapter = NoteAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()
        checkRVType()
    }


    private fun initialize() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListeners() = with(binding) {
        fab.setOnClickListener {
            findNavController().navigate(R.id.noteDetailFragment)
        }

        imgRvType.setOnClickListener {
            val sharedPreference = SharedPreference()
            sharedPreference.unit(requireContext())
            val isRVLinear = sharedPreference.isRVLinear

            if (isRVLinear) {
                sharedPreference.isRVLinear = false
                imgRvType.setImageResource(R.drawable.ic_list)
                rv.layoutManager = GridLayoutManager(requireContext(), 2)
            } else {
                sharedPreference.isRVLinear = true
                imgRvType.setImageResource(R.drawable.ic_grid)
                rv.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    private fun checkRVType() {
        val sharedPreference = SharedPreference()
        sharedPreference.unit(requireContext())
        val isRVLinear = sharedPreference.isRVLinear

        if (!isRVLinear) {
            with(binding) {
                imgRvType.setImageResource(R.drawable.ic_list)
                rv.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        } else {
            with(binding) {
                imgRvType.setImageResource(R.drawable.ic_grid)
                rv.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удаплить?")
            setPositiveButton("Да") { dialog, which ->
                App.appDatabase?.noteDao()?.deleteNoteModel(noteModel)
            }
            setNeutralButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}