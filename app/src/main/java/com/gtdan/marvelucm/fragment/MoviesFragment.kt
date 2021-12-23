package com.gtdan.marvelucm.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.gtdan.marvelucm.adapter.ItemsListAdapter
import com.gtdan.marvelucm.databinding.FragmentMoviesBinding
import com.gtdan.marvelucm.viewmodel.MoviesViewModel

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = ItemsListAdapter {
            val action = MoviesFragmentDirections.actionMoviesFragmentToDescriptionActivity(
                "movie", it.id.toString()
            )
            view.findNavController().navigate(action)
        }

        binding.recyclerViewMovies.adapter = adapter
    }

}