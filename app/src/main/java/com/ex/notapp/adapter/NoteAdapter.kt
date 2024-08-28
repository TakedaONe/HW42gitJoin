package com.ex.notapp.adapter

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ex.notapp.data.models.NoteModel
import com.ex.notapp.databinding.ItemNoteBinding
import com.ex.notapp.interfaces.OnClickItem
import java.util.Date
import java.util.Locale

class NoteAdapter(private val onLongClick: OnClickItem, private val onClick:OnClickItem) : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
        private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        fun bind(item: NoteModel) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            if (!item.color.isNullOrEmpty()) {
                val color = try {
                    Color.parseColor(item.color)
                } catch (e: IllegalArgumentException) {

                    Color.GRAY
                }
                binding.itemColor.setBackgroundColor(color)
            }
            val currentDate = dateFormat.format(Date())
            val currentTime = timeFormat.format(Date())
            binding.tvDate.text = currentDate
            binding.tvTime.text = currentTime
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClickItem(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem

        }

    }
}