package com.ex.notapp.ui.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ex.notapp.R
import com.ex.notapp.adapter.ChatAdapter
import com.ex.notapp.databinding.FragmentChatBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val chatAdapter = ChatAdapter()
    private val db = Firebase.firestore
    private lateinit var query: Query
    private lateinit var listener: ListenerRegistration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
        setUpListener()
        observeMessages()
    }

    private fun initialise() {
        binding.rvChat.apply {
            adapter = chatAdapter
        }
    }

    private fun setUpListener() {
        binding.btnSend.setOnClickListener {
            val user = hashMapOf(
                "name" to binding.etMessage.text.toString()
            )
            db.collection("users").add(user).addOnCompleteListener {
            }
            binding.etMessage.text.clear()
        }
    }

    private fun observeMessages() {
        query = db.collection("user")
        listener = query.addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            value?.let { snapshot ->
                val messages = mutableListOf<String>()
                for (doc in snapshot.documents) {
                    val message = doc.getString("name")
                    message?.let {
                        messages.add(message)
                    }
                }
             chatAdapter.submitList(messages)
            }
        }
    }
}


