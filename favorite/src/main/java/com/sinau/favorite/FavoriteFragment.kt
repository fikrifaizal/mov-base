package com.sinau.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sinau.core.ui.MovieAdapter
import com.sinau.favorite.databinding.FragmentFavoriteBinding
import com.sinau.favorite.di.DaggerFavoriteComponent
import com.sinau.movbase.di.FavoriteModuleDependencies
import com.sinau.movbase.view.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        _binding = FragmentFavoriteBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            showFavoriteMovies()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showFavoriteMovies() {
        val movieAdapter = MovieAdapter()

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.countFavoriteMovie.observe(viewLifecycleOwner) { count ->
            if (count >= 1) {
                favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner) { movie ->
                    if (movie != null) {
                        movieAdapter.setData(movie)
                        binding?.rvMovie?.visibility = View.VISIBLE
                        binding?.tvEmpty?.visibility = View.GONE
                    } else {
                        binding?.rvMovie?.visibility = View.GONE
                        binding?.tvEmpty?.visibility = View.VISIBLE
                    }
                }
            } else {
                binding?.rvMovie?.visibility = View.GONE
                binding?.tvEmpty?.visibility = View.VISIBLE
            }
        }

        with(binding?.rvMovie) {
            this?.layoutManager = GridLayoutManager(activity, 2)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }
}