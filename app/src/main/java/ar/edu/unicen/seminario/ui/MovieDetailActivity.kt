package ar.edu.unicen.seminario.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.edu.unicen.seminario.databinding.ActivityMovieDetailBinding
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener los datos de la película desde el Intent
        val title = intent.getStringExtra("movie_title") ?: ""
        val overview = intent.getStringExtra("movie_overview") ?: ""
        val posterPath = intent.getStringExtra("movie_poster_path") ?: ""

        // Configurar la vista con los datos de la película
        binding.movieTitle.text = title
        binding.movieOverview.text = overview
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$posterPath")
            .into(binding.moviePoster)

        binding.backButton.setOnClickListener {
            finish() // Cierra la actividad actual y regresa a la anterior
        }
    }


}