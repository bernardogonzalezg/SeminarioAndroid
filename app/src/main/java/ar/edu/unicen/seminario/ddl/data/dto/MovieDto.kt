package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import ar.edu.unicen.seminario.ddl.models.Movie
import com.google.gson.annotations.SerializedName

@Keep
class MovieDto(
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("overview")
    val overview: String

) {

    fun toMovie(): Movie {
        return Movie(
            title= original_title,
            poster_path = poster_path,
            overview= overview)


    }
}