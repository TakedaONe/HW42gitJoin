package com.ex.notapp.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ex.notapp.R
import com.ex.notapp.databinding.ActivityMainBinding
import com.ex.notapp.utills.App
import com.ex.notapp.utills.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(this)


    }

    override fun onStart() {
        super.onStart()
        checkEnter()

    }

    private fun checkEnter() {

        if (preferenceHelper.firstEnter) {
            navController.navigate(R.id.onBoardFragment)
            preferenceHelper.firstEnter = false

        } else {
            navController.navigate(R.id.notFragment)
        }
    }
}