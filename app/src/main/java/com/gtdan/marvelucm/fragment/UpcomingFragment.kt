package com.gtdan.marvelucm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gtdan.marvelucm.adapter.ItemUpcomingAdapter
import com.gtdan.marvelucm.databinding.FragmentUpcomingBinding
import com.gtdan.marvelucm.viewmodel.UpcomingViewModel

class UpcomingFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding
    private val viewModel: UpcomingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerViewUpcoming.adapter = ItemUpcomingAdapter()
    }

}