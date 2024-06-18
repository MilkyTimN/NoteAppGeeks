package com.example.noteappgeeks.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.noteappgeeks.R
import com.example.noteappgeeks.databinding.ActivityMainBinding
import com.example.noteappgeeks.utils.SharedPreference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        checkOnboarding()
    }

    private fun checkOnboarding() {
        val sharedPreference = SharedPreference()
        sharedPreference.unit(this)

        val onBoardingShown = sharedPreference.isBoard

        if (!onBoardingShown) {
            navController.navigate(R.id.onBoardFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}