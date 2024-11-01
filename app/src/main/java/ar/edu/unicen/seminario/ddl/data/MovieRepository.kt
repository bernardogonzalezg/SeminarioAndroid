package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.Movie
import ar.edu.unicen.seminario.ddl.models.MovieResult
import retrofit2.Response
import javax.inject.Inject

class MovieRepository  @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) {


    suspend fun  getPopularMovies (

    ): List<Movie>? {
        return movieRemoteDataSource.getPopularMovies()

    }


}