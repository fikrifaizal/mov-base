package com.sinau.movbase.view.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sinau.movbase.R
import com.sinau.movbase.core.domain.model.Movie
import com.sinau.movbase.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import com.sinau.movbase.BuildConfig as BC

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(movie)

        supportActionBar?.title = getString(R.string.detail_story)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load(BC.POSTER_URL + movie.posterPath)
                    .centerCrop()
                    .into(ivPoster)
                tvTitle.text = movie.title
                tvOverview.text = movie.overview
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}