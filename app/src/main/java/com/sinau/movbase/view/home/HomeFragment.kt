package com.sinau.movbase.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sinau.movbase.core.data.Resource
import com.sinau.movbase.core.ui.MovieAdapter
import com.sinau.movbase.databinding.FragmentMovieBinding
import com.sinau.movbase.view.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showPopularMovies()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPopularMovies() {
        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        homeViewModel.movies.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when(movie) {
                    is Resource.Loading -> binding?.loadingLayout?.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding?.loadingLayout?.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding?.loadingLayout?.visibility = View.GONE
                    }
                }
            }
        }

        with(binding?.rvMovie) {
            this?.layoutManager = LinearLayoutManager(activity)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }
}