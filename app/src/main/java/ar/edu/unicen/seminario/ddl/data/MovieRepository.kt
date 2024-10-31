package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.Movie
import ar.edu.unicen.seminario.ddl.models.MovieResult
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi) {

    suspend fun getPopularMovies(): List<Movie>? {
        val response: Response<MovieResult> = movieApi.getPopularMovies()
        return if (response.isSuccessful) {
            response.body()?.results // Suponiendo que tienes un atributo 'results' en MovieResult
        } else {
            null // O manejar el error según sea necesario
        }
    }
}