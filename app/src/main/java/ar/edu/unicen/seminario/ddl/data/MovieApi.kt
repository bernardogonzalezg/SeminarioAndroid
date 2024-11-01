package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.data.dto.MovieResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    /*@GET("movie/popular/")
    suspend fun  getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResultDto>*/

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Constants.API_KEY // Usar la constante aquí
    ): Response<MovieResultDto>



}