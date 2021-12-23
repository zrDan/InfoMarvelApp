package com.gtdan.marvelucm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.gtdan.marvelucm.adapter.ItemsListAdapter
import com.gtdan.marvelucm.databinding.FragmentSeriesBinding
import com.gtdan.marvelucm.viewmodel.SeriesViewModel

class SeriesFragment : Fragment() {
    private lateinit var binding: FragmentSeriesBinding
    private val viewModel: SeriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = ItemsListAdapter {
            val action = SeriesFragmentDirections.actionSeriesFragmentToDescriptionActivity(
                "serie", it.id.toString()
            )
            view.findNavController().navigate(action)
        }

        binding.recyclerViewSeries.adapter = adapter
    }

}