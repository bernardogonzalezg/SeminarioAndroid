package ar.edu.unicen.seminario.ddl.data

import android.util.Log
import ar.edu.unicen.seminario.ddl.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun  getPopularMovies (

    ): List<Movie>? {
        return withContext(Dispatchers.IO) {
            try {

                val response = movieApi.getPopularMovies()
                Log.e("MovieApi", "Error: ${response.code()} - ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    // Procesa la respuesta aquí
                } else {
                    Log.e("MovieRemoteDataSourse", "Error: ${response.code()} - ${response.message()}")
                    return@withContext null
                }


                return@withContext response.body()?.results?.map {it.toMovie()

                }
            }
            catch (e: Exception) {

                e.printStackTrace()
                return@withContext null
            }

        }
    }
}