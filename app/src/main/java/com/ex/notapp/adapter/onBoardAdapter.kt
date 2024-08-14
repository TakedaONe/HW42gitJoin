package com.ex.notapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ex.notapp.ui.fragments.onboard.onBoardPagingFragment
import com.ex.notapp.ui.fragments.onboard.onBoardPagingFragment.Companion.ARG_ONBOARD_POSITION

class onBoardAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int) = onBoardPagingFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)
        }

    }

}