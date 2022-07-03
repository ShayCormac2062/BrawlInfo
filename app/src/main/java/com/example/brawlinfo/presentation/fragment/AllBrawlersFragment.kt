package com.example.brawlinfo.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brawlinfo.R
import com.example.brawlinfo.databinding.FragmentAllBrawlersBinding

class AllBrawlersFragment : Fragment() {

    private var binding: FragmentAllBrawlersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBrawlersBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.tvHello?.text = getString(R.string.app_name)
    }

}