package com.example.noteappgeeks.ui.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappgeeks.R
import com.example.noteappgeeks.data.models.NoteModel
import com.example.noteappgeeks.data.models.enums.NoteColorEnum
import com.example.noteappgeeks.databinding.ItemNoteBinding
import com.example.noteappgeeks.interfaces.Clickable
import com.example.noteappgeeks.ui.fragments.note.NoteFragment
import com.example.noteappgeeks.ui.fragments.note.NoteFragmentDirections
import java.time.format.DateTimeFormatter

class NoteAdapter(private val clickable: Clickable) : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("ResourceAsColor")
        fun bind(item: NoteModel) {
            binding.tvTitle.text = item.title
            binding.tvNote.text = item.description

            val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            binding.tvDate.text = item.addDate.format(dateFormatter)

            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
            binding.tvTime.text = item.addDate.format(timeFormatter)

            when(item.noteColor) {
                NoteColorEnum.RED -> binding.root.setBackgroundResource(R.drawable.item_note_bg_red)
                NoteColorEnum.WHITE -> binding.root.setBackgroundResource(R.drawable.item_note_bg_white)
                else -> binding.root.setBackgroundResource(R.drawable.item_note_bg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener{
            clickable.onLongClick(getItem(position))
            true
        }

        holder.itemView.setOnClickListener {
            clickable.onClick(getItem(position))
        }
//        holder.binding.root.setOnClickListener {
//            val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(getItem(position).id)
//            it.findNavController().navigate(action)
//        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {

        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }
    }
}