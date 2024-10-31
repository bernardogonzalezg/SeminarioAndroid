package ar.edu.unicen.seminario.ui

import MovieAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unicen.seminario.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import android.view.View

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeToViewModel()
        loadMovies() // Carga las películas
    }

    private fun subscribeToViewModel() {
        viewModel.loading.onEach { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }.launchIn(lifecycleScope)

        viewModel.movies.onEach { movies ->
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = MovieAdapter(movies ?: emptyList()) { movie ->
                Toast.makeText(this, "${movie.title} clicked", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            binding.errorView.visibility = if (error) View.VISIBLE else View.GONE
        }.launchIn(lifecycleScope)
    }

    private fun loadMovies() {
        viewModel.getPopularMovies() // Llama al ViewModel para cargar películas
    }
}