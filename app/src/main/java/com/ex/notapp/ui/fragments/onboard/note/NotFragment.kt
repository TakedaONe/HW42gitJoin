package com.ex.notapp.ui.fragments.onboard.note

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex.notapp.R
import com.ex.notapp.adapter.NoteAdapter
import com.ex.notapp.data.models.NoteModel
import com.ex.notapp.databinding.FragmentNotBinding
import com.ex.notapp.interfaces.OnClickItem
import com.ex.notapp.utills.App
import com.ex.notapp.utills.PreferenceHelper
import com.google.android.material.navigation.NavigationView

class NotFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNotBinding
    private var noteAdapter = NoteAdapter(this, this)
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotBinding.inflate(inflater, container, false)
        sharedPreferences = PreferenceHelper.customPrefs(requireContext(), "layout_pref")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        getData()
        setUpListeners()
        drawerNavigation()
    }

    private fun initialize() {
        val isGridLayout = sharedPreferences.getBoolean("isGridLayout", false)
        if (isGridLayout) {
            binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.changeForm.visibility = View.INVISIBLE
            binding.changeForm2.visibility = View.VISIBLE
        } else {
            binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
            binding.changeForm.visibility = View.VISIBLE
            binding.changeForm2.visibility = View.INVISIBLE
        }
        binding.rvNote.adapter = noteAdapter
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    private fun setUpListeners() {
        binding.btnPlus.setOnClickListener {
            findNavController().navigate(R.id.action_notFragment_to_detailFragment)
        }
        binding.changeForm.setOnClickListener {
            binding.rvNote.layoutManager = GridLayoutManager(requireContext(), 2)
            saveLayoutPreference(true)
            binding.changeForm.visibility = View.INVISIBLE
            binding.changeForm2.visibility = View.VISIBLE
        }
        binding.changeForm2.setOnClickListener {
            binding.rvNote.layoutManager = LinearLayoutManager(requireContext())
            saveLayoutPreference(false)
            binding.changeForm.visibility = View.VISIBLE
            binding.changeForm2.visibility = View.INVISIBLE
        }
    }

    private fun saveLayoutPreference(isGridLayout: Boolean) {
        sharedPreferences.edit().putBoolean("isGridLayout", isGridLayout).apply()
    }

    override fun onLongClickItem(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить Запись?")
            setPositiveButton("Нет") { dialog, _ ->
                dialog.cancel()
            }
            setNegativeButton("Да") { dialog, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }.show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NotFragmentDirections.actionNotFragmentToDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
    private fun drawerNavigation() {
        val drawerLayout = binding.drawerLayout
        val navView = binding.navView

        binding.drawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home-> {
                    findNavController().navigate(R.id.signFragment)

                }
                R.id.nav_note -> {
                    findNavController().navigate(R.id.notFragment)

                }
                R.id.nav_messages -> {
                    findNavController().navigate(R.id.chatFragment)

                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
    }

