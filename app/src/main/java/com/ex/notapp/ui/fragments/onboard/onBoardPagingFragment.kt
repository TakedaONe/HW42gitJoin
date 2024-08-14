package com.ex.notapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import com.ex.notapp.R
import com.ex.notapp.databinding.FragmentOnBoardPagingBinding


class onBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiliaze()

    }

    companion object {
        const val ARG_ONBOARD_POSITION = "arg_onboard_position"

    }

    private fun initiliaze() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                tvFirst.text = "Очень удобный функционал"
                lottieView.setAnimation("lottie1.json")
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()

            }


            1 -> {
                tvFirst.text = "Быстрый, качественный продукт"
                lottieView.setAnimation("lottie2.json")
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()

            }

            2 -> {
                tvFirst.text = "Куча функций и интересных фишек"
                lottieView.setAnimation("lottie3.json")
                lottieView.repeatCount = LottieDrawable.INFINITE
                lottieView.playAnimation()


            }
        }


    }


}