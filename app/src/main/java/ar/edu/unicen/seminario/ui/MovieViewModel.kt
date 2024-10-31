package ar.edu.unicen.seminario.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario.ddl.data.MovieRepository
import ar.edu.unicen.seminario.ddl.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>?>(null)
    val movies = _movies.asStateFlow()

    fun getPopularMovies() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _movies.value = null

            // Llama al repositorio para obtener todas las películas populares
            val movies = movieRepository.getPopularMovies()

            if (movies != null) {
                _movies.value = movies // Actualiza la lista de películas
            } else {
                _error.value = true // Marca error si no hay películas
            }

            _loading.value = false
        }
    }
}