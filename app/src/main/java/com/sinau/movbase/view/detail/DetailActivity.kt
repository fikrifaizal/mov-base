package com.sinau.movbase.view.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sinau.core.domain.model.Movie
import com.sinau.movbase.R
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
                tvDate.text = movie.releaseDate
                rbRate.rating = movie.voteAverage.toFloat() / 2
                tvRate.text = movie.voteAverage.toString()
                tvOverview.text = movie.overview

                var statusFavorite = movie.isFavorite
                setStatusFavorite(statusFavorite)
                btnFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteMovie(movie, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }

                btnShare.setOnClickListener {
                    val shareText = "Movie Title\t: ${movie.title}" +
                            "\nRelease Date\t: ${movie.releaseDate}" +
                            "\nMovie Rating\t: ${tvRate.text}" +
                            "\nMovie Overview\t: " + movie.overview

                    val shareActivity = Intent(Intent.ACTION_SEND)
                    shareActivity.putExtra(Intent.EXTRA_TEXT, shareText)
                    shareActivity.type = "text/plain"
                    startActivity(Intent.createChooser(shareActivity, "Share with"))
                }
            }
        }
    }

    private fun setStatusFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnFavorite.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_file_checkmark_24,
                0,
                0,
                0
            )
        } else {
            binding.btnFavorite.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_file_add_24,
                0,
                0,
                0
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}