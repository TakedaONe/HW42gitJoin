package com.ex.notapp.ui.fragments.onboard.note

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ex.notapp.R
import com.ex.notapp.data.models.NoteModel
import com.ex.notapp.databinding.FragmentDetailBinding
import com.ex.notapp.utills.App
import java.util.Date
import java.util.Locale

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var noteId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateNote()
        setUpListeners()
        getRealTime()
    }

    private fun updateNote() {
        arguments?.let {
            noteId = it.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val argsNote = App.appDatabase?.noteDao()?.getById(noteId)
            argsNote?.let { model ->
                val color = Color.parseColor(model.color)
                binding.etTitle.setText(model.title)
                binding.etDescription.setText(model.description)
                binding.detailFragment.setBackgroundColor(color)
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setUpListeners() {
        binding.txtReady.setOnClickListener {
            val etTitle = binding.etTitle.text.toString().trim()
            val etDescription = binding.etDescription.text.toString().trim()
            val drawable = binding.detailFragment.background
            val color = if (drawable is ColorDrawable) {
                drawable.color
            } else {
                Color.TRANSPARENT
            }
            val fragmentColor = String.format("#%06X", (0xFFFFFF and color))
            if (noteId != -1) {
                val updateNote = NoteModel(etTitle, etDescription, fragmentColor)
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updateNote)
            } else {
                App.appDatabase?.noteDao()?.insert(NoteModel(etTitle, etDescription, fragmentColor))
            }
            findNavController().navigateUp()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.fon1.setOnClickListener {
            binding.detailFragment.setBackgroundColor(Color.parseColor("#0E0C0C"))
            binding.dot.translationX = 0f
        }
        binding.fon2.setOnClickListener {
            binding.detailFragment.setBackgroundColor(Color.parseColor("#EBE6E6"))
            binding.dot.translationX = 300f
        }
        binding.fon3.setOnClickListener {
            binding.detailFragment.setBackgroundColor(Color.parseColor("#571818"))
            binding.dot.translationX = 600f
        }
    }

    private fun getRealTime() {
        val dateFormat = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
        val timeFormat = SimpleDateFormat("H:mm", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        val currentTime = timeFormat.format(Date())
        binding.txtTime.text = currentTime
        binding.txtDate.text = currentDate
    }
}
