package com.ex.notapp.ui.fragments.onboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ex.notapp.R
import com.ex.notapp.databinding.FragmentOnBoardBinding
import com.ex.notapp.adapter.onBoardAdapter
import com.ex.notapp.ui.fragments.onboard.note.NotFragment
import com.google.android.material.tabs.TabLayoutMediator


class onBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUplisteners()
        starNotApp()
        dotsChange()

    }


    private fun initialize() {
        binding.viewPager2.adapter = onBoardAdapter(this)
    }

    private fun setUplisteners() {


        binding.tvPass.setOnClickListener {
            if (binding.viewPager2.currentItem < 3) {
                binding.viewPager2.setCurrentItem(binding.viewPager2.currentItem + 2, true)
            }
        }

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 2) {
                    binding.tvPass.visibility = View.INVISIBLE
                    binding.tvStart.visibility = View.VISIBLE

                } else {
                    binding.tvPass.visibility = View.VISIBLE
                    binding.tvStart.visibility = View.INVISIBLE
                }
            }


        })
    }

    private fun dotsChange() = with(binding.viewPager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.dot1.setBackgroundResource(R.drawable.active_dot)
                } else {
                    binding.dot1.setBackgroundResource(R.drawable.non_active_dot)
                }
                if (position == 1) {
                    binding.dot2.setBackgroundResource(R.drawable.active_dot)
                } else {
                    binding.dot2.setBackgroundResource(R.drawable.non_active_dot)
                }
                if (position == 2) {
                    binding.dot3.setBackgroundResource(R.drawable.active_dot)
                } else {
                    binding.dot3.setBackgroundResource(R.drawable.non_active_dot)
                }
            }
        })


    }

    private fun starNotApp() {
        binding.tvStart.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_notFragment,
                null,
                navOptions {
                    anim {
                        enter = R.anim.slide_in_right
                    }

                })
        }
    }


}

