package com.example.brawlinfo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brawlinfo.R
import com.example.brawlinfo.databinding.ActivityMainBinding
import com.example.brawlinfo.presentation.fragment.AllBrawlersFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        begin()
    }

    private fun begin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AllBrawlersFragment())
            .commit()
    }
}